package com.example.venomshop.data

import com.example.venomshop.R
import com.example.venomshop.model.Clothes
import com.example.venomshop.model.Info

object DataSource {
    val clothes = listOf<Clothes>(
        Clothes(
            title = R.string.stone_island_1,
            brand = R.string.stone_island,
            description = R.string.stone_island_1_desc,
            price = "74.990",
            grade = 4.9,
            images = listOf(R.drawable.stone_island_upd, R.drawable.stone_island_1_1)
        ),
        Clothes(
            title = R.string.cp_company_1,
            brand = R.string.cp_company,
            description = R.string.cp_company_1_desc,
            price = "25.490",
            grade = 4.6,
            images = listOf(R.drawable.cp_company_1, R.drawable.cp_company_1_1)
        ),
        Clothes(
            title = R.string.adidas_1,
            brand = R.string.adidas,
            description = R.string.adidas_1_desc,
            price = "11.500",
            grade = 4.8,
            images = listOf(R.drawable.adidas_1, R.drawable.adidas_1_1, R.drawable.adidas_1_2)
        ),
        Clothes(
            title = R.string.weekend_offender_1,
            brand = R.string.weekend_offender,
            description = R.string.weekend_offender_1_desc,
            price = "6.000",
            grade = 3.7,
            images = listOf(R.drawable.weekend_offender_1, R.drawable.weekend_offender_1_1)
        ),
        Clothes(
            title = R.string.adidas_2,
            brand = R.string.adidas,
            description = R.string.adidas_2_desc,
            price = "19.299",
            grade = 3.9,
            images = listOf(R.drawable.adidas_2, R.drawable.adidas_2_1, R.drawable.adidas_2_2)
        ),
        Clothes(
            title = R.string.cp_company_2,
            brand = R.string.cp_company,
            description = R.string.cp_company_2_desc,
            price = "8.540",
            grade = 5.0,
            images = listOf(R.drawable.cp_company_2)
        ),
        Clothes(
            title = R.string.alpha_1,
            brand = R.string.alpha,
            description = R.string.alpha_1_desc,
            price = "21.590",
            grade = 4.0,
            images = listOf(R.drawable.alpha_1)
        ),
        Clothes(
            title = R.string.cp_company_3,
            brand = R.string.cp_company,
            description = R.string.cp_company_3_desc,
            price = "10.990",
            grade = 2.4,
            images = listOf(R.drawable.cp_company_3, R.drawable.cp_company_3_1)
        ),
    )
    val info = listOf<Info>(
        Info(
            title = R.string.info_title_1,
            body = R.string.info_body_1
        ),
        Info(
            title = R.string.info_title_2,
            body = R.string.info_body_2
        ),
        Info(
            title = R.string.info_title_3,
            body = R.string.info_body_3
        ),
        Info(
            title = R.string.info_title_4,
            body = R.string.info_body_4
        ),
        Info(
            title = R.string.info_title_5,
            body = R.string.info_body_5
        )
    )
}