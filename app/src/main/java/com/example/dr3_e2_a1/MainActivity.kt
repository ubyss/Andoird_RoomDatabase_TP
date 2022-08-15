package com.example.dr3_e2_a1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.room.Room
import com.example.dr3_e2_a1.roomContactInfo.ContatoInfo
import com.example.dr3_e2_a1.roomContactInfo.ContatoInfoDao
import com.example.dr3_e2_a1.roomContactInfo.ContatoInfoDatabase
import com.example.dr3_e2_a1.roomContato.Contato
import com.example.dr3_e2_a1.roomContato.ContatoDao
import com.example.dr3_e2_a1.roomContato.ContatoDatabase

class MainActivity : AppCompatActivity() {

    lateinit var contatoDao: ContatoDao
    lateinit var contatoInfoDao: ContatoInfoDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this,
            ContatoDatabase::class.java,
            "contatos.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        contatoDao = db.obterContatoDAO()

        val dbInfo = Room.databaseBuilder(
            this,
            ContatoInfoDatabase::class.java,
            "contatosInfo.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        contatoInfoDao = dbInfo.obterContatoDAO()

        this.atualizarLista()


        this.findViewById<Button>(R.id.btnGravar).setOnClickListener{

            var txtNome = this.findViewById<EditText>(R.id.txtNome)
            var txtFone = this.findViewById<EditText>(R.id.txtFone)

            if(txtNome.text.isEmpty() || txtFone.text.isEmpty()){
                Toast.makeText(this@MainActivity, "Não é possível salvar dados em branco", Toast.LENGTH_LONG).show()
            } else {
                val contato = Contato(
                    null,
                    txtNome.text.toString(),
                    txtFone.text.toString(),
                )

                val contactInfo = ContatoInfo(
                    null,
                    null,
                    null,
                )

                contatoInfoDao.insert(contactInfo)

                contatoDao.insert(contato)

                txtNome.text = null
                txtFone.text = null

                this.atualizarLista()
                }

        }

        this.findViewById<ListView>(R.id.listView).setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, ContactActivity::class.java)
            intent.putExtra("nome", contatoDao.listar()[i].nome)
            intent.putExtra("id", i)
            startActivity(intent)
        }
    }

    private fun atualizarLista(){
        val contatos = contatoDao.listar()
        val nomes = ArrayList<String?>()
        for (i in contatos.indices){
            nomes.add("${contatos[i].nome}")
        }
        val lstContato = this.findViewById<ListView>(R.id.listView)
        lstContato.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes)
    }

}