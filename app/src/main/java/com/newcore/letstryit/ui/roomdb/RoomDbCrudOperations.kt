package com.newcore.letstryit.ui.roomdb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.core.util.adapters.ButtonsAdapter
import com.newcore.letstryit.core.util.adapters.ElevatedButton
import com.newcore.letstryit.core.util.adapters.TableRowData
import com.newcore.letstryit.databinding.FragmentButtonsAdapterBinding
import com.newcore.letstryit.ui.showtabledata.ShowTableDataFragmentDirections

class RoomDbCrudOperations :
    BaseFragment<FragmentButtonsAdapterBinding>(FragmentButtonsAdapterBinding::inflate) {

    private val vm: RoomDbCrudOperationsViewModel by viewModels()

    private val buttonsAdapter: ButtonsAdapter by lazy {
        ButtonsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonsRecycler(binding, buttonsAdapter)
        initLoadingListener()

        buttonsAdapter.items = listOf(
            ElevatedButton(
                name = "go to Data from school",
                description = "one to one relation",
                onClick = {
                    vm.getSchools().observe(viewLifecycleOwner) { rows ->
                        findNavController().navigate(
                            ShowTableDataFragmentDirections.actionGlobalShowTableDataFragment(
                                TableRowData("school name", "directory name"),
                                rows.map { row ->
                                    TableRowData(row.school.schoolName,
                                        row.directory.directoryName)
                                }.toTypedArray()
                            )
                        )
                    }

                }
            ),
            ElevatedButton(
                name = "go to Data from Category",
                description = "one to one relation",
                onClick = {
                    vm.getDirectories().observe(viewLifecycleOwner) { rows ->
                        findNavController().navigate(
                            ShowTableDataFragmentDirections.actionGlobalShowTableDataFragment(
                                TableRowData("school name", "directory name"),
                                rows.map { row ->
                                    TableRowData(row.school.schoolName,
                                        row.directory.directoryName)
                                }.toTypedArray()
                            )
                        )
                    }

                }
            ),
            ElevatedButton(
                name = "get category from school one",
                description = "one to one relation",
                onClick = {
                    vm.getSchoolWithDirectoryName().observe(viewLifecycleOwner) { rows ->
                        findNavController().navigate(
                            ShowTableDataFragmentDirections.actionGlobalShowTableDataFragment(
                                TableRowData("school name", "directory name"),
                                rows.map { row ->
                                    TableRowData(
                                        row.school.schoolName,
                                        row.directory.directoryName,
                                    )
                                }.toTypedArray()
                            )
                        )
                    }

                }
            ),

            ElevatedButton(
                name = "get school from directory one",
                description = "one to one relation",
                onClick = {
                    vm.getDirectoryWithSchoolName().observe(viewLifecycleOwner) { rows ->
                        findNavController().navigate(
                            ShowTableDataFragmentDirections.actionGlobalShowTableDataFragment(
                                TableRowData("school name", "directory name"),
                                rows.map { row ->
                                    TableRowData(
                                        row.school.schoolName,
                                        row.directory.directoryName,
                                    )
                                }.toTypedArray()
                            )
                        )
                    }

                }
            ),

            ElevatedButton(
                name = "insert school",
                onClick = {
                    vm.insertSchool()
                }
            ),
            ElevatedButton(
                name = "insert Directory",
                onClick = {
                    vm.insertDirectory()

                }
            ),
        )
    }

    private fun initLoadingListener() {
        vm.loadingLiveData.observe(viewLifecycleOwner) {
            toggleLoading(it)
        }
    }


}

