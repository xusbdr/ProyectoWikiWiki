package com.jes.wikiworld

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jes.wikiworld.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        // Ocultar el menú inferior al principio
        binding.boton.visibility = View.GONE

        // Listener para el campo de nombre
        binding.nombre.addTextChangedListener { text ->
            // Verificar si el texto no está vacío y mostrar el menú inferior si es así
            binding.boton.visibility = if (text.toString().trim().isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        // Listener para el botón de inicio de sesión
        binding.boton.setOnClickListener {
            val userName = binding.nombre.text.toString().trim()
            if (userName.isNotEmpty()) {
                Singleton.setUserName(userName)
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                Toast.makeText(
                    requireContext(),
                    "¡Bienvenido de vuelta, $userName!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Por favor, ingresa tu nombre para continuar.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}