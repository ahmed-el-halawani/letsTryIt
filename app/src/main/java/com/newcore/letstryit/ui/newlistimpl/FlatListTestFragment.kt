package com.newcore.letstryit.ui.newlistimpl

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentFlatListTestBinding
import com.newcore.letstryit.databinding.ItemIntentInfoButtonBinding
import com.newcore.letstryit.databinding.ItemTableRowBinding
import com.newcore.letstryit.ui.newlistimpl.lib.destroyRvList
import com.newcore.letstryit.ui.newlistimpl.lib.rvList

class FlatListTestFragment :
    BaseFragment<FragmentFlatListTestBinding>(FragmentFlatListTestBinding::inflate) {

    private val vm: FlatListTestViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList(
            binding.rvList,
            GridLayoutManager(context, 3),
        ) {
            generateViews(
                binding = ItemIntentInfoButtonBinding::inflate,
                children = List(1000) { "ahmed $it" }
            ) { v, i ->
                v.tvDescription.text = i
                v.tvName.text = i
            }

            generateViews(
                binding = ItemTableRowBinding::inflate,
                children = List(1000) { "gomaa $it" }
            ) { v, i ->
                v.tvColumn1.text = i
                v.tvColumn2.text = i
                v.tvColumn3.text = i
                v.tvColumn4.text = i
            }
        }


        //        rvList(
        //            binding = ItemTableRowBinding::inflate,
        //            emptyList<String>(),
        //            binding.rvList
        //        ) { v, it ->
        //            v.tvColumn1.text = it
        //            v.tvColumn2.text = it
        //            v.tvColumn3.text = it
        //            v.tvColumn4.text = it
        //        }


    }

    override fun onDestroy() {
        super.onDestroy()
        destroyRvList()
    }

}

