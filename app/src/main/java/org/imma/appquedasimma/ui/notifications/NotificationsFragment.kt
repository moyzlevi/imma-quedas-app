package org.imma.appquedasimma.ui.notifications

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import org.imma.appquedasimma.dao.MainDatabase
import org.imma.appquedasimma.databinding.FragmentNotificationsBinding
import org.imma.appquedasimma.entities.EmergencyContactEntity


class NotificationsFragment : Fragment() {
    companion object {
        val TAG = "NotificationsFragment"
    }

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var btnContatoEmergencia: Button
    private lateinit var tvContatoSalvo: TextView
//    private lateinit var startCreateActivityForResult: ActivityResultLauncher<Intent>
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var database: MainDatabase


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = Room.databaseBuilder(requireActivity().applicationContext, MainDatabase::class.java, "SQLITE_DATABASE")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        tvContatoSalvo = binding.tvContatoSalvo
        val listaDeContatosSalvos = database.roomEmergencyContactDao().getAllFromDb()
        if(listaDeContatosSalvos.isNotEmpty()){
            tvContatoSalvo.text = "Contato salvo: ${listaDeContatosSalvos.last().name}"
        }else{
            tvContatoSalvo.text = "Contato salvo:"
        }

        btnContatoEmergencia = binding.btnContatoEmergencia
        btnContatoEmergencia.setOnClickListener {
            val pickContact = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE)
            startActivityForResult(pickContact, 1);

        }
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            val contactData: Uri? = data?.data
            if(data!= null){
                val c: Cursor? = context?.contentResolver?.query(contactData!!,null,null,null,null)
                if(c!=null && c.moveToFirst()){
                    val phoneIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val nameIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    val num = c.getString(phoneIndex)
                    val contactName = c.getString(nameIndex)

                    val tempContact:EmergencyContactEntity = EmergencyContactEntity()
                    tempContact.name = contactName
                    tempContact.phoneNumber = num
                    val contactToSave = arrayListOf(tempContact)
                    database.roomEmergencyContactDao().insertAll(contactToSave)

                    Log.i("NotificationsFragment","${num} - ${contactName}")
                    Log.i("NotificationsFragment","Achado do DB: ${database.roomEmergencyContactDao().findByWord(contactName)}")
                    tvContatoSalvo.text = "Contato salvo: ${contactName}"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}