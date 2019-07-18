package hr.foreal.showsmarkosoljic.ui.dialog


import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.addEpisode.AddEpisodeFragment
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import kotlinx.android.synthetic.main.choose_picture_layout.*


class ChoosePictureLocation() : DialogFragment() {
    companion object {
        @JvmStatic
        fun newInstance(): ChoosePictureLocation {
            return ChoosePictureLocation()
        }
    }

    private val PERMISSION_REQUEST_CODE = 200
    private lateinit var fragment: AddEpisodeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_picture_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCamera.setOnClickListener {
            (activity as MainActivity).checkPermission();
            dismiss()
        }
        tvGallery.setOnClickListener {
            (activity as MainActivity).galleryCheck()
            dismiss()
        }
    }


}
