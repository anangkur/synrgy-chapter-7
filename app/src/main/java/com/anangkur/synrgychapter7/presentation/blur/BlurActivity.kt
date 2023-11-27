package com.anangkur.synrgychapter7.presentation.blur

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.work.WorkInfo
import com.anangkur.synrgychapter7.helper.worker.KEY_IMAGE_URI
import com.anangkur.synrgychapter7.Application
import com.anangkur.synrgychapter7.databinding.ActivityBlurBinding
import com.anangkur.synrgychapter7.helper.requestPermissions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File
import javax.inject.Inject

class BlurActivity : AppCompatActivity() {

    companion object {

        private const val REQUEST_CODE_PERMISSION = 0
        private const val MIME_TYPE_IMAGE = "image/*"
        private const val PHOTO_FILE_PREFIX = "IMG_"
        private const val PHOTO_FILE_SUFFIX = ".jpg"

        fun startActivity(context: Context) {
            context.startActivity(Intent(context, BlurActivity::class.java))
        }
    }

    private val binding by lazy { ActivityBlurBinding.inflate(layoutInflater) }

    @Inject
    lateinit var viewModel: BlurViewModel
    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val permissionsAndroid13 = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_MEDIA_IMAGES,
    )
    private val galleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent(),
        ::galleryResultCallback,
    )
    private val cameraResult = registerForActivityResult(
        ActivityResultContracts.TakePicture(),
        ::cameraResultCallback,
    )
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as Application).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeLiveData()

        viewModel.loadProfilePhoto()

        binding.buttonGo.setOnClickListener { viewModel.applyBlur() }
        binding.ivBlur.setOnClickListener { checkingPermissions() }
    }

    private fun observeLiveData() {
        viewModel.getOutputWorkerInfo().observe(this, ::handleWorkerInfos)
        viewModel.error.observe(this, ::handleError)
        viewModel.profilePhoto.observe(this, ::handleProfilePhoto)
    }

    private fun handleWorkerInfos(workerInfos: List<WorkInfo>) {
        if (workerInfos.isEmpty()) {
            return
        }

        val workerInfo = workerInfos.last()
        if (workerInfo.state.isFinished) {
            val outputImageUrl = workerInfo.outputData.getString(KEY_IMAGE_URI)
            if (!outputImageUrl.isNullOrEmpty()) {
                viewModel.setOutputUri(outputImageUrl)
                viewModel.saveProfilePhoto(outputImageUrl)
                viewModel.loadProfilePhoto()
            }
        } else {
            // todo handle in progress
        }
    }

    private fun checkingPermissions() {
        requestPermissions(
            permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissionsAndroid13
            } else {
                permissions
            },
            requestCode = REQUEST_CODE_PERMISSION,
            doIfGranted = ::showChooseImageDialog,
        )
    }

    private fun showChooseImageDialog() {
        MaterialAlertDialogBuilder(this)
            .setMessage("Choose Image")
            .setPositiveButton("Gallery") { _, _ -> showGallery() }
            .setNegativeButton("Camera") { _, _ -> showCamera() }
            .show()
    }

    private fun showGallery() {
        intent.type = MIME_TYPE_IMAGE
        galleryResult.launch(MIME_TYPE_IMAGE)
    }

    private fun showCamera() {
        val photoFile = File.createTempFile(
            PHOTO_FILE_PREFIX,
            PHOTO_FILE_SUFFIX,
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        )
        photoUri = FileProvider.getUriForFile(
            this,
            "${this.packageName}.provider",
            photoFile,
        )
        cameraResult.launch(photoUri)
    }

    private fun galleryResultCallback(uri: Uri?) {
        handleResultCallback(uri)
    }

    private fun cameraResultCallback(result: Boolean) {
        if (result) {
            handleResultCallback(photoUri)
        }
    }

    private fun handleResultCallback(result: Uri?) {
        result?.toString()?.let { uriString ->
            viewModel.saveProfilePhoto(uriString)
            binding.ivBlur.setImageURI(result)
            viewModel.setImageUri(uriString)
        }
    }

    private fun handleProfilePhoto(profilePhoto: String?) {
        profilePhoto?.let { binding.ivBlur.setImageURI(Uri.parse(profilePhoto)) }
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}