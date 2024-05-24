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
            price = "70.000",
            grade = 4.9,
            image = R.drawable.stone_island
        ),
        Clothes(
            title = R.string.cp_company_1,
            brand = R.string.cp_company,
            description = R.string.cp_company_1_desc,
            price = "120.000",
            grade = 4.1,
            image = R.drawable.cp_company_1
        ),
        Clothes(
            title = R.string.adidas_1,
            brand = R.string.adidas,
            description = R.string.adidas_1_desc,
            price = "12.000",
            grade = 4.6,
            image = R.drawable.adidas_1
        ),
        Clothes(
            title = R.string.adidas_2,
            brand = R.string.adidas,
            description = R.string.adidas_2_desc,
            price = "3.000",
            grade = 3.9,
            image = R.drawable.adidas_2
        ),
        Clothes(
            title = R.string.adidas_legend,
            brand = R.string.adidas,
            description = R.string.adidas_legend,
            price = "999.999",
            grade = 5.0,
            image = R.drawable.adidas_legend
        )
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