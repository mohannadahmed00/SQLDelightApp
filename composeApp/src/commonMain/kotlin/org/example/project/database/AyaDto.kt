package org.example.project.database

data class AyaDto(
    val id: Int?,
    val jozz: Int,
    val sura_no: Int,
    val sura_name_en: String,
    val sura_name_ar: String,
    val page: Int,
    val line_start: Int,
    val line_end: Int,
    val aya_no: Int,
    val aya_text: String,
    val aya_text_emlaey: String
)
