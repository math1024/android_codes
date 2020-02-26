package com.android.lyc.ui.navi

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.onNavDestinationSelected
import com.android.lyc.R

/**
 * @author rosetta
 * @date 2020/02/25
 */
class NavigationHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.navigation_host_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = navOptions {
            anim {
                enter = android.R.anim.slide_in_left
                exit = android.R.anim.slide_out_right
                popEnter = android.R.anim.slide_in_left
                popExit = android.R.anim.slide_out_right
            }
        }

        val button = view.findViewById<AppCompatButton>(R.id.navigate_destination_button)
        button?.setOnClickListener {
            findNavController().navigate(R.id.flow_step_one_dest, null, options)
        }

        // second
//        view.findViewById<AppCompatButton>(R.id.navigate_action_button)?.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.next_action, null)
//        )

        // third
        view.findViewById<AppCompatButton>(R.id.navigate_action_button)?.setOnClickListener {
            val flowStepNumberArg = 2
            val action = NavigationHomeFragmentDirections.nextAction(flowStepNumberArg)
            findNavController().navigate(action)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
    }
}