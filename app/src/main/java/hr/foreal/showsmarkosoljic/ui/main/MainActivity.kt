package hr.foreal.showsmarkosoljic.ui.main

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.repository.TvShowRepositoryImpl
import hr.foreal.showsmarkosoljic.router.RouterImpl
import hr.foreal.showsmarkosoljic.ui.addEpisode.AddEpisodeFragment
import hr.foreal.showsmarkosoljic.ui.tvShowsList.TvShowsListFragment
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import hr.foreal.showsmarkosoljic.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    val CAMERA_REQUEST_CODE = 10
    val GALLERY_REQUEST_CODE = 11
    var isTablet = false
    var isLandscape = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isTablet = resources.getBoolean(R.bool.isTablet)
        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(
                    TvShowRepositoryImpl()
                ) as T
            }
        })[MainViewModel::class.java]
        viewModel.setRouter(RouterImpl(this@MainActivity, supportFragmentManager, isTablet, isLandscape))
        checkOrientation()
        if (savedInstanceState == null) {
            viewModel.showWelcomeFragment(intent.getStringExtra(LoginViewModel.INTENT_KEY))
        }


    }

    private fun checkOrientation() {
        if (isTablet && isLandscape) {
            if (supportFragmentManager.findFragmentByTag("DETAIL_FRAGMENT") != null) {
                supportFragmentManager.beginTransaction()
                    .remove(supportFragmentManager.findFragmentByTag("DETAIL_FRAGMENT")!!)
                    .replace(R.id.masterLayoutContainer, TvShowsListFragment.newInstance())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.masterLayoutContainer, TvShowsListFragment.newInstance())
                    .commit()
            }

        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {//TODO Make it pretty
            CAMERA_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                openCamera()

            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        showMessageOKCancel("You cannot use this feature without camera and external storage approval", //todo extract to resources
                            DialogInterface.OnClickListener { dialog, which ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestCameraPermission()
                                }
                            })
                    }
                }
            }
            GALLERY_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                openGallery()

            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        showMessageOKCancel("You cannot use this feature without camera and external storage approval",//todo extract to resources
                            DialogInterface.OnClickListener { dialog, which ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestGalleryPermission()
                                }
                            })
                    }
                }
            }
        }
    }

    private fun requestCameraPermission() {
        requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            CAMERA_REQUEST_CODE
        )


    }

    private fun requestGalleryPermission() {
        requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            GALLERY_REQUEST_CODE
        )


    }


    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    fun checkCameraPermission() {
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            openCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun openCamera() = startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST_CODE)


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null && requestCode == CAMERA_REQUEST_CODE) {
            (supportFragmentManager.findFragmentByTag(AddEpisodeFragment.ADD_EPISODE_FRAGMENT_TAG) as AddEpisodeFragment)
                .setImage(data.extras?.get("data") as Bitmap)
            Log.d("marko", "$requestCode")
        } else if (resultCode == Activity.RESULT_OK && data != null && requestCode == GALLERY_REQUEST_CODE) {


            (supportFragmentManager.findFragmentByTag(AddEpisodeFragment.ADD_EPISODE_FRAGMENT_TAG) as AddEpisodeFragment)
                .setImage(Images.Media.getBitmap(this.contentResolver, data.data))
            Log.d("marko", "$requestCode")
        }


    }


    fun checkGalleryPermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openGallery()
        } else {
            requestGalleryPermission()
        }


    }


    private fun openGallery() = startActivityForResult(
        Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI),
        GALLERY_REQUEST_CODE
    )


}


