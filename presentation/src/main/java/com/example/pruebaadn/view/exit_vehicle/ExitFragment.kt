package com.example.pruebaadn.view.exit_vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebaadn.databinding.FragmentExitBinding
import java.util.*
import javax.inject.Inject

class ExitFragment : Fragment(), ExitViewModelDelegate {

    @Inject
    lateinit var exitViewModel: ExitViewModel
    private var _binding: FragmentExitBinding? = null
    private var dateExit: Date = Date()
    private var check: com.example.infraestructure.data_access.models.CheckModels? = null
    private var vehicle: com.example.infraestructure.data_access.models.VehicleModels? = null
    private var cylinder = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        exitViewModel.setDelegate(this)

        _binding = FragmentExitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listenerActionButton()

        return root
    }

    private fun listenerActionButton(){
        _binding?.buttonExitVehicle?.setOnClickListener {
            val idCheck = _binding?.editTextExit?.text.toString()
//            exitViewModel.getConsultCheckAndVehicle(idCheck.toInt())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun responseNotCheckForId() {
//        _binding?.textViewCosteExit?.text = getString(R.string.not_check)
//            App.getContext()?.createToast(getString(R.string.not_check))
//    }
//
//    override fun responseConsultCheckAndVehicle(check: com.example.infraestructure.data_access.models.ConsultCheckAndVehicle) {
//        this.check = check.checkModels
//        this.vehicle = check.vehicleModels
//    }
//
//    override fun responseDetailVehicleForVehicleId(detailVehicle: com.example.infraestructure.data_access.models.DetailsVehicleModels) {
//        cylinder = detailVehicle.value?.toInt()!!
//    }
//
//    override fun responsePricesWithDetails(list: List<com.example.infraestructure.data_access.models.variant_init.PricesModels>) {
//        check?.dateExit = dateExit.convertToFormatString(DateFormats.ISO_8601)
//        val array = check?.dateInput?.convertToFormatDate()?.dateDifference()
//        if (array!![0] == 0 && array[1] == 0){
//            _binding?.textViewCosteExit?.post {
//                _binding?.textViewCosteExit?.text =
//                    exitViewModel.validateCosteHoursCeroForType(list, cylinder).toString()
//            }
//            return
//        }
//        calculateTotalCoste(array[0], array[1],list)
//    }
//
//    override fun responsePricesNotDetails(list: List<com.example.infraestructure.data_access.models.variant_init.PricesModels>) {
//        check?.dateExit = dateExit.convertToFormatString(DateFormats.ISO_8601)
//        val array = check?.dateInput?.convertToFormatDate()?.dateDifference()
//        if (array!![0] == 0 && array[1] == 0){
//            _binding?.textViewCosteExit?.post {
//                _binding?.textViewCosteExit?.text =
//                    exitViewModel.validateCosteHoursCeroForType(list, null).toString()
//            }
//            return
//        }
//        calculateTotalCoste(array[0], array[1],list)
//    }

    private fun calculateTotalCoste(day: Int, hours: Int, listPrice: List<com.example.infraestructure.data_access.models.variant_init.PricesModels>){
        var total = 0
        if (cylinder >= 500) {
//            total = exitViewModel.validateCoste(day, hours, listPrice) + listPrice[0].extra?.toInt()!!
//        } else total = exitViewModel.validateCoste(day, hours, listPrice)
            _binding?.textViewCosteExit?.post {
                _binding?.textViewCosteExit?.text = total.toString()
            }
            check?.totalCost = total.toDouble()
//        exitViewModel.updateCheck(check!!)
        }
    }
}