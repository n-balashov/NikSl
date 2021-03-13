package com.pavesid.niksl.extensions

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.open(block: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        block()
        commit()
    }
}
