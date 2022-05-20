package com.newcore.letstryit.ui.pushnotification

import androidx.fragment.app.viewModels
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentPushNotificationBinding

class PushNotificationFragment :
    BaseFragment<FragmentPushNotificationBinding>(FragmentPushNotificationBinding::inflate) {

    private val vm: PushNotificationFragmentViewModel by viewModels()

}

