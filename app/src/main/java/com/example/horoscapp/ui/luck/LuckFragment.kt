package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import com.example.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {

    //Crear Binding
    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!! //Devuelve el _binding sin ser nulo

    @Inject
    lateinit var  randomCardProvider: RandomCardProvider //inyecta la clase de CardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    //Funcion que prepara la predicion
    private fun preparePrediction() {
        val currentLuck = randomCardProvider.getLucky()
        //Asegura que no es nulable
        currentLuck?.let {luck ->
            val currentPrediccion = getString(luck.text)
            binding.tvLucky.text = currentPrediccion
            binding.ivLuckyCard.setImageResource(luck.img)
            binding.tvShare.setOnClickListener { shareResult(currentPrediccion) }
        }
    }

    //Funcion para compartir
    private fun shareResult(prediction:String) {

        //Guarda el intent con el put extra de prediccion y tipo texto
        val sendIntent:Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        //Comparte el intent segun a la app que se le click
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun initListeners() {
        binding.ivRoulette.setOnClickListener { spinRoulette() }
    }

    //Animacion giro de ruleta
    private fun spinRoulette() {

        val random = Random()
        val degress = random.nextInt(1440) + 360 //cuantos grados maximos(4 vueltas) + (minimo una vuelta)

        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degress.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator() //Disminuya la velocidad
        animator.doOnEnd { slideCard() }
        animator.start()

    }

    //Animacion slide de card
    private fun slideCard(){
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up) //llama la animacion desde xml

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            //Cuando inicie la animacion
            override fun onAnimationStart(p0: Animation?) {
                binding.reverse.isVisible = true
            }
            //Cuando finalice la animacion
            override fun onAnimationEnd(p0: Animation?) {
                growCard() //agrande card
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.reverse.startAnimation(slideUpAnimation) //inicie la animacion
    }

    //Funcion para crecer animacion
    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.reverse.isVisible = false //Desaparezca la vista uno
                showPromonitionView()
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.reverse.startAnimation((growAnimation))
    }

    private fun showPromonitionView() {
        val dissapearAnimation = AlphaAnimation(1.0f, 0.0f) //dasaparecer vista uno
        dissapearAnimation.duration= 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f) //Aparecer vista doss
        appearAnimation.duration = 1000

        dissapearAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false //Desaparecer vista uno
                binding.prediction.isVisible = true //Aparecer vista dos
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.preview.startAnimation(dissapearAnimation) //se inician las animaciones
        binding.prediction.startAnimation(appearAnimation) //se inician las abnimaciones
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}