package com.example.dr3_e2_a1

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.dr3_e2_a1.roomContactInfo.ContatoInfo
import com.example.dr3_e2_a1.roomContactInfo.ContatoInfoDao
import com.example.dr3_e2_a1.roomContactInfo.ContatoInfoDatabase
import com.example.dr3_e2_a1.roomContato.Contato
import com.example.dr3_e2_a1.roomContato.ContatoDao
import com.example.dr3_e2_a1.roomContato.ContatoDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_contact.*


class ContactActivity : AppCompatActivity() {

    lateinit var contatoDao: ContatoDao
    lateinit var contatoInfoDao: ContatoInfoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

//        -------------------------------------------------------------------------------

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

//        -------------------------------------------------------------------------------

        var currentId = intent.getIntExtra("id", 1)

        Toast.makeText(this, "$currentId and ${contatoInfoDao.listar()[0].email}", Toast.LENGTH_LONG).show()



//        -------------------------------------------------------------------------------
        getCurrentItem(currentId)

        btnDelete.setOnClickListener{
            deleteCurrentItem(currentId)
        }

        btnAtualizar.setOnClickListener {
            updateCurrentItem(currentId)
        }
    }

    fun getCurrentItem(id:Int){
        val Nome = txtContactName
        val Fone = txtContactFone
        val Email = txtContactEmail
        val adress = txtContactAdress

        Nome.setText(contatoDao.listar()[id].nome)
        Fone.setText(contatoDao.listar()[id].Fone)

        Email.setText(contatoInfoDao.listar()[id].email)
        adress.setText(contatoInfoDao.listar()[id].adress)
    }

    fun deleteCurrentItem(id:Int){
        contatoDao.delete(contatoDao.listar()[id])
        contatoInfoDao.delete(contatoInfoDao.listar()[id])

        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    fun updateCurrentItem(id:Int){
        val Nome = txtContactName
        val Fone = txtContactFone
        val Email = txtContactEmail
        val adress = txtContactAdress

        val contatoAtualizado = Contato(
            contatoDao.listar()[id].id,
            Nome.text.toString(),
            Fone.text.toString()
        )
        contatoDao.update(contatoAtualizado)

        val contatoInfoAtualizado = ContatoInfo(
            contatoInfoDao.listar()[id].id,
            Email.text.toString(),
            adress.text.toString()
        )
        contatoInfoDao.update(contatoInfoAtualizado)


        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

    }
}