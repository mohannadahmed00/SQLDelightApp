package org.example.project.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.database.HafsDatabase

interface AyatRepository {
    suspend fun getAyatBySuraNo(suraNo: Int): List<AyaDto>
    suspend fun getAllAyat(): List<AyaDto>
    suspend fun getAyaById(id: Int): AyaDto?
    suspend fun getAyatBySuraAndAyaNo(suraNo: Int, ayaNo: Int): AyaDto?
}

class AyaRepositoryImpl(
    private val database: HafsDatabase
) : AyatRepository {

    private fun mapToAya(
        id: Long?,
        jozz: Long,
        sura_no: Long,
        sura_name_en: String,
        sura_name_ar: String,
        page: Long,
        line_start: Long,
        line_end: Long,
        aya_no: Long,
        aya_text: String,
        aya_text_emlaey: String
    ): AyaDto {
        return AyaDto(
            id = id?.toInt(),
            jozz = jozz.toInt(),
            sura_no = sura_no.toInt(),
            sura_name_en = sura_name_en,
            sura_name_ar = sura_name_ar,
            page = page.toInt(),
            line_start = line_start.toInt(),
            line_end = line_end.toInt(),
            aya_no = aya_no.toInt(),
            aya_text = aya_text,
            aya_text_emlaey = aya_text_emlaey
        )
    }

    override suspend fun getAyatBySuraNo(suraNo: Int): List<AyaDto> = withContext(Dispatchers.IO) {
        database.ayatQueries.getAyatBySuraNo(suraNo.toLong()).executeAsList().map { ayat ->
            mapToAya(
                ayat.id, ayat.jozz, ayat.sura_no, ayat.sura_name_en,
                ayat.sura_name_ar, ayat.page, ayat.line_start, ayat.line_end,
                ayat.aya_no, ayat.aya_text, ayat.aya_text_emlaey
            )
        }
    }

    override suspend fun getAllAyat(): List<AyaDto> = withContext(Dispatchers.IO) {
        database.ayatQueries.getAllAyat().executeAsList().map { ayat ->
            mapToAya(
                ayat.id, ayat.jozz, ayat.sura_no, ayat.sura_name_en,
                ayat.sura_name_ar, ayat.page, ayat.line_start, ayat.line_end,
                ayat.aya_no, ayat.aya_text, ayat.aya_text_emlaey
            )
        }
    }

    override suspend fun getAyaById(id: Int): AyaDto? = withContext(Dispatchers.IO) {
        database.ayatQueries.getAyaById(id.toLong()).executeAsOneOrNull()?.let { ayat ->
            mapToAya(
                ayat.id, ayat.jozz, ayat.sura_no, ayat.sura_name_en,
                ayat.sura_name_ar, ayat.page, ayat.line_start, ayat.line_end,
                ayat.aya_no, ayat.aya_text, ayat.aya_text_emlaey
            )
        }
    }

    override suspend fun getAyatBySuraAndAyaNo(suraNo: Int, ayaNo: Int): AyaDto? =
        withContext(Dispatchers.IO) {
            database.ayatQueries.getAyatBySuraAndAyaNo(suraNo.toLong(), ayaNo.toLong())
                .executeAsOneOrNull()?.let { ayat ->
                    mapToAya(
                        ayat.id, ayat.jozz, ayat.sura_no, ayat.sura_name_en,
                        ayat.sura_name_ar, ayat.page, ayat.line_start, ayat.line_end,
                        ayat.aya_no, ayat.aya_text, ayat.aya_text_emlaey
                    )
                }
        }
}