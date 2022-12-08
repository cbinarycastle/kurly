package io.github.cbinarycastle.kurly.ui.section

import android.os.Parcelable
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.domain.model.SectionType
import kotlinx.parcelize.Parcelize

@Parcelize
data class SectionModel(
    val id: Int,
    val title: String,
    val type: SectionType,
) : Parcelable

fun Section.toUiModel() = SectionModel(
    id = id,
    title = title,
    type = type
)