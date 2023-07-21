package com.example.markakodportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.markakodportal.adapter.PersonsAdapter
import com.example.markakodportal.databinding.FragmentPersonsBinding

class PersonsFragment : Fragment() {

    private var _binding: FragmentPersonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileList = createDummyData()

        val profileAdapter = PersonsAdapter(profileList)
        binding.rcylerPersons.layoutManager = LinearLayoutManager(requireContext())
        binding.rcylerPersons.adapter = profileAdapter
    }

    private fun createDummyData(): List<Profile> {
        val dummyData = mutableListOf<Profile>()


        dummyData.add(Profile("Ayberk Temin","ayberkt@gmail.com", "Stajyer","https://media.licdn.com/dms/image/C4D03AQHGK0voeZK66A/profile-displayphoto-shrink_800_800/0/1662224359014?e=2147483647&v=beta&t=orno7saubT3HezSnCLUNFFC24iRrTtb95ReUztgJ_Z4"))
        dummyData.add(Profile( "Feyyaz Çakabey","feyyazcaka@gmail.com", "Ceo","https://media.licdn.com/dms/image/C4E03AQEl9rVOlv0UlA/profile-displayphoto-shrink_800_800/0/1600092651232?e=1695254400&v=beta&t=d_jcg0c2vt710CIcEsJ0YmRwTE3CGHaZsaEq_v0YHBw"))
        dummyData.add(Profile( "Yusuf Albayrak","yusufalbyrk@gmail.com", "Project Lead","https://media.licdn.com/dms/image/C5603AQFJGAtFz2dc-Q/profile-displayphoto-shrink_800_800/0/1546379045773?e=2147483647&v=beta&t=_seM317wOdlQZV4YEoZylDr1v_-YQROeSqchG8m0BV4"))
        dummyData.add(Profile( "Zehra Demircan","zhradmrcn@gmail.com", "Team Lead","https://pps.whatsapp.net/v/t61.24694-24/358498502_1259398768281732_4266850499991214349_n.jpg?ccb=11-4&oh=01_AdQ7Z8AVTUomgVoJOzZewt-KCdi6uf0f0-ghqjZQ4ePCZA&oe=64C7777A"))
        dummyData.add(Profile( "Serkan Ateş","serkanats@gmail.com", "Senior Developer","https://media.licdn.com/dms/image/C4D03AQHc3SAt3-dK4g/profile-displayphoto-shrink_800_800/0/1632778488208?e=2147483647&v=beta&t=dCbjCE04oR-RG4CdLPQpMk71CdfIRY1TDWA4-Bl07o4"))
        dummyData.add(Profile( "Harun Hekimoğlu","harun@gmail.com", "Backend Developer","https://media.licdn.com/dms/image/C4E03AQEcH2MP8VtKwQ/profile-displayphoto-shrink_800_800/0/1627403275924?e=2147483647&v=beta&t=AcOiCZS-9agA8ZTuWP4V-Zf7vRWxa8Pa8shLyFrFPk4"))


        return dummyData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
