package com.example.do_an_cs3.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.example.do_an_cs3.Fragment.Profile.LoveFragment
import com.example.do_an_cs3.LoginActivity
import com.example.do_an_cs3.R
import com.example.do_an_cs3.SignupActivity
import com.example.do_an_cs3.activity.profile.SetUpProfileActivity
import com.example.do_an_cs3.databinding.FragmentProfileBinding
import com.example.do_an_cs3.utils.Utils
import io.paperdb.Paper
import io.reactivex.rxjava3.disposables.CompositeDisposable

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentProfileBinding.inflate(inflater,container,false)
        compositeDisposable = CompositeDisposable()
        Paper.init(requireContext())

        if(Paper.book().read<String>("user") == null){
            binding.btnLogin.setOnClickListener {
                val intent = Intent(requireActivity(),LoginActivity::class.java)
                startActivity(intent)
            }
            binding.btnSign.setOnClickListener {
                val intent = Intent(requireContext(),SignupActivity::class.java)
                startActivity(intent)
            }
        }else{
            RenderInfoUser()

            if(savedInstanceState == null){
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoveFragment()).commit()

            }

            binding.btnLogout.setOnClickListener {
                Paper.book().delete("user")

                val intent = Intent(requireContext(),LoginActivity::class.java)
                startActivity(intent)
            }

            binding.btnSetup.setOnClickListener {
                startActivity(Intent(requireContext(),SetUpProfileActivity::class.java))
            }

        }


        return binding.root
    }

    private fun RenderInfoUser() {
        binding.btnLogin.visibility = View.GONE
        binding.btnSign.visibility = View.GONE
        binding.btnLogout.visibility = View.VISIBLE
        binding.infoUser.visibility = View.VISIBLE
        val uri : String = Utils.user_current.avatar.trim()
        if(uri != ""){
            binding.imgUser.setImageURI(uri.toUri())
        }

        binding.UserName.text = Utils.user_current.name
        binding.Email.text = Utils.user_current.email

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}