package hong.sy.chowall.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hong.sy.chowall.R
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.FragmentListChunBinding

class ListChunFragment : Fragment() {
    private var _binding: FragmentListChunBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val datas = mutableListOf<ListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListChunBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecycler()

        return view
    }

    private fun initRecycler() {
        listAdapter = ListRecyclerAdapter(binding.root.context)
        binding.rvListChun.adapter = listAdapter
        binding.rvListChun.addItemDecoration(VerticalItemDecorator(70))

        datas.apply {
            add(ListData(img = R.drawable.ex_img_list, name = "상상마당춘천스테이", address = "전주시 완산구 풍남동 은행로 은행로 45", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
            add(ListData(img = R.drawable.ex_img_list, name = "남부닭갈비", address = "강원도 춘천시 공지로 357", phone = "033-243-9966", time = "매일 17:00-22:00" ))
            add(ListData(img = 234, name = "델모니코스(Error)", address = "강원도 춘천시 동면 순환대로 1154-106", phone = "033-252-0999" ))
            add(ListData(name = "라모스버거(Null)", address = "강원도 춘천시 옛경춘로 835" ))
            add(ListData(img = R.drawable.ex_img_list, name = "온더가든", address = "강원도 춘천시 남산면 종자리로 21", phone = "033-262-9339", time = "매일 10:00-22:00" ))
            add(ListData(img = R.drawable.ex_img_list, name = "곰배령", address = "강원도 춘천시 춘천로 1층", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
        }

        listAdapter.datas = datas
        listAdapter.notifyDataSetChanged()
    }
}