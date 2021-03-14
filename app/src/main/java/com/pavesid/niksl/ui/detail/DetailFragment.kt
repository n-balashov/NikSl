package com.pavesid.niksl.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.pavesid.niksl.R
import com.pavesid.niksl.core.viewBinding
import com.pavesid.niksl.data.model.Achievement
import com.pavesid.niksl.databinding.FragmentDetailBinding
import com.pavesid.niksl.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()
    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)
    private val mainActivity by lazy { activity as MainActivity }

    private val messageAdapter: MessageAdapter = MessageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAccount()
        initViews()
        subscribe()
    }

    private fun initAccount() {
        GoogleSignIn.getLastSignedInAccount(mainActivity)?.let {
            successAuth(it)
            return
        }
    }

    private fun requestAuth() {
        val gso = Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestProfile()
            .build()
        startActivityForResult(GoogleSignIn.getClient(mainActivity, gso).signInIntent, 100)
    }

    private fun successAuth(account: GoogleSignInAccount?) {
        viewModel.account = account
        messageAdapter.account = account

        binding.apply {
            etMessage.visibility = VISIBLE
            send.visibility = VISIBLE
            auth.visibility = GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                successAuth(task.getResult(ApiException::class.java))
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(mainActivity, "Error when auth", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViews() {
        binding.apply {
            root.setOnClickListener {}
            send.setOnClickListener {
                if (etMessage.text.toString().isNotEmpty()) {
                    viewModel.writeMessage(etMessage.text.toString())
                    etMessage.text.clear()
                }
            }

            rvMessages.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = messageAdapter
            }

            auth.setOnClickListener { requestAuth() }
        }
    }

    private fun subscribe() {
        viewModel.messages.observe(this.viewLifecycleOwner) {
            messageAdapter.messages = it
        }

        binding.apply {
            achieveLayout.text.text = viewModel.achievement.name
            achieveLayout.image.load(viewModel.achievement.imagePath)
            achieveLayout.leftOverlay.isVisible = false
            achieveLayout.rightOverlay.isVisible = false
        }
    }

    companion object {

        const val ACHIEVEMENT_ARG = "achievement"

        fun newInstance(achievement: Achievement): DetailFragment {
            val detailFragment = DetailFragment()
            val arguments = Bundle()
            arguments.putParcelable(ACHIEVEMENT_ARG, achievement)
            detailFragment.arguments = arguments
            return detailFragment
        }
    }
}
