package hong.sy.chowall.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hong.sy.chowall.R
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.FragmentListJeonBinding

class ListJeonFragment : Fragment() {
    private var _binding: FragmentListJeonBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val datas = mutableListOf<ListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListJeonBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecycler()

        return view
    }

    private fun initRecycler() {
        listAdapter = ListRecyclerAdapter(binding.root.context)
        binding.rvListJeon.adapter = listAdapter
        binding.rvListJeon.addItemDecoration(VerticalItemDecorator(70))

        datas.apply {
            add(ListData(img = R.drawable.ex_img_list, name = "곰배령3", address = "강원도 춘천시 춘천로 1층", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
            add(ListData(img = R.drawable.ex_img_list, name = "델모니코스", address = "강원도 춘천시 동면 순환대로 1154-106", phone = "033-252-0999", time = "11:00-22:00" ))
            add(ListData(img = R.drawable.ex_img_list, name = "남부닭갈비", address = "강원도 춘천시 공지로 357", phone = "033-243-9966", time = "매일 17:00-22:00" ))
            add(ListData(img = R.drawable.ex_img_list, name = "라모스버거", address = "강원도 춘천시 옛경춘로 835", phone = "0507-1402-0006", time = "매일 11:00-22:00", breakTime = "(브레이크타임 15:00-16:00)" ))
            add(ListData(img = R.drawable.ex_img_list, name = "온더가든", address = "강원도 춘천시 남산면 종자리로 21", phone = "033-262-9339", time = "매일 10:00-22:00" ))
            add(ListData(img = R.drawable.ex_img_list, name = "곰배령", address = "강원도 춘천시 춘천로 1층", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
        }

        listAdapter.datas = datas
        listAdapter.notifyDataSetChanged()
    }
}