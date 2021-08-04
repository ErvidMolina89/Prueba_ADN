package com.example.domain.builder

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.model.CheckEntity
import com.example.domain.model.DisponibilityEntity
import com.example.domain.model.PricesEntity
import com.example.domain.model.VehicleEntity

class EntityObjectMother {
    companion object {
        //VehicleEntity
        fun vehiclePlateCarSucces(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("ABD456")
                .buildVehicle()
        }

        fun vehiclePlateLowerCaseFailure(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("adh456")
                .buildVehicle()
        }

        fun vehiclePlateLowerCaseAndUpperCaseFailure(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("Fdh456")
                .buildVehicle()
        }
        fun vehiclePlateCarIncomplete(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("ABD")
                .buildVehicle()
        }
        fun vehiclePlateCarWithPlateMoreLettersSuccess(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("GTD25R")
                .buildVehicle()
        }
        fun vehiclePlateCarWithPlateMoreNumbersFailure(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("AB4526")
                .buildVehicle()
        }
        fun vehiclePlateCarWithLongestPlate(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("ABDG456")
                .buildVehicle()
        }
        fun vehiclePlateCarWithPlateEmpty(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("")
                .buildVehicle()
        }
        //CheckEntity
        fun createCheckWithCorrectData(): CheckEntity {
            return CheckEntityBuilder()
                .withPlate("AFG456")
                .withDateInput("2021-08-01T20:00:00")
                .buildCheck()
        }
        fun createCheckkWithNullPlateFailure(): CheckEntity {
            return CheckEntityBuilder()
                .withPlate(null)
                .withDateInput("2021-08-01T20:00:00")
                .buildCheck()
        }
        fun createCheckkWithNullDateInputFailure(): CheckEntity {
            return CheckEntityBuilder()
                .withPlate("GHT456")
                .withDateInput(null)
                .buildCheck()
        }
        fun enterCorrectCheckVehicleSuccess(): CheckEntity {
            return CheckEntityBuilder()
                .withPlate("THF456")
                .withDateInput("2021-07-30T20:00:00")
                .buildCheck()
        }
        //DisponibilityEntity
        fun disponibilityCar(): DisponibilityEntity {
            return DispomibilityEntityBuilder()
                .withId(1)
                .withTypeId(1)
                .withCount(20)
                .buildDisponibility()
        }
        fun disponibilityMotocycle(): DisponibilityEntity {
            return DispomibilityEntityBuilder()
                .withId(2)
                .withTypeId(2)
                .withCount(10)
                .buildDisponibility()
        }
        //PriceEntity
        fun priceDaysCar(): PricesEntity {
            return PriceEntityBuilder()
                .withTypeId(1)
                .withTypePrice(1)
                .withValue(8000.0)
                .buildPrice()
        }
        fun priceDayMotocycler(): PricesEntity {
            return PriceEntityBuilder()
                .withTypeId(2)
                .withTypePrice(1)
                .withValue(4000.0)
                .buildPrice()
        }
        fun priceHoursCar(): PricesEntity {
            return PriceEntityBuilder()
                .withTypeId(1)
                .withTypePrice(2)
                .withValue(1000.0)
                .buildPrice()
        }
        fun priceHoursMotocycler(): PricesEntity {
            return PriceEntityBuilder()
                .withTypeId(2)
                .withTypePrice(2)
                .withValue(500.0)
                .buildPrice()
        }
        //CheckVehicle
        fun costVehicleOneDayAndTreeHours(): VehicleAggregate {
            val check = CheckEntityBuilder()
                .withPlate("LKJ456")
                .withDateInput("2021-07-30T20:00:00")
                .withDateExit("2021-07-31T23:00:00")
                .buildCheck()
            return VehicleAggregateBuilder()
                .withPlate("LKJ456")
                .withTypeId(1)
                .withCheckEntity(check)
                .buildAggregate()
        }
        fun costVehicleCarLessThanAnHour(): VehicleAggregate {
            val check = CheckEntityBuilder()
                .withPlate("LKJ456")
                .withDateInput("2021-07-31T20:00:00")
                .withDateExit("2021-07-31T20:00:00")
                .buildCheck()
            return VehicleAggregateBuilder()
                .withPlate("LKJ456")
                .withTypeId(1)
                .withCheckEntity(check)
                .buildAggregate()
        }
        fun costVehicleMotoMoreCylinder(): VehicleAggregate {
            val check = CheckEntityBuilder()
                .withPlate("LKJ456")
                .withDateInput("2021-07-31T11:59:00")
                .withDateExit("2021-08-01T10:10:00")
                .buildCheck()
            return VehicleAggregateBuilder()
                .withPlate("LKJ456")
                .withTypeId(2)
                .withCylinder("650")
                .withCheckEntity(check)
                .buildAggregate()
        }
        fun costVehicleMoreCylinderMotoLessThanAnHour(): VehicleAggregate {
            val check = CheckEntityBuilder()
                .withPlate("LKJ456")
                .withDateInput("2021-08-01T09:30:00")
                .withDateExit("2021-08-01T10:10:00")
                .buildCheck()
            return VehicleAggregateBuilder()
                .withPlate("LKJ456")
                .withTypeId(2)
                .withCylinder("650")
                .withCheckEntity(check)
                .buildAggregate()
        }
        fun costVehicleLessCylinderMotoLessThanAnHour(): VehicleAggregate {
            val check = CheckEntityBuilder()
                .withPlate("LKJ456")
                .withDateInput("2021-08-01T09:30:00")
                .withDateExit("2021-08-01T10:10:00")
                .buildCheck()
            return VehicleAggregateBuilder()
                .withPlate("LKJ456")
                .withTypeId(2)
                .withCylinder("450")
                .withCheckEntity(check)
                .buildAggregate()
        }
        fun costVehicleMotoLessCylinder(): VehicleAggregate {
            val check = CheckEntityBuilder()
                .withPlate("LKJ456")
                .withDateInput("2021-08-01T09:10:00")
                .withDateExit("2021-08-02T20:10:00")
                .buildCheck()
            return VehicleAggregateBuilder()
                .withPlate("LKJ456")
                .withTypeId(2)
                .withCylinder("450")
                .withCheckEntity(check)
                .buildAggregate()
        }
        //VehicleService
        fun insertVehicleCorrect(): VehicleEntity{
            return VehicleEntityBuilder()
                .withPlate("RFR456")
                .buildVehicle()
        }
    }
}