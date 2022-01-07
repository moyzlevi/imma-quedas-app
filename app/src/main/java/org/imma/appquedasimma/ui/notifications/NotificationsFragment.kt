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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.imma.appquedasimma.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment() {
    companion object {
        val TAG = "NotificationsFragment"
    }

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var btnContatoEmergencia: Button
//    private lateinit var startCreateActivityForResult: ActivityResultLauncher<Intent>
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
                Log.i("NotificationsFragment","${num} - ${contactName}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}