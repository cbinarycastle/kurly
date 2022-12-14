package io.github.cbinarycastle.kurly.data.section

import com.google.gson.annotations.SerializedName
import io.github.cbinarycastle.kurly.domain.model.Page
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.domain.model.SectionType

data class GetSectionsResponse(
    val data: List<SectionInfo>,
    val paging: PagingInfo?,
)

data class SectionInfo(
    val id: Int,
    val title: String,
    val type: String,
    val url: String,
)

data class PagingInfo(@SerializedName("next_page") val nextPage: Int)

fun GetSectionsResponse.toSectionPage(currentPage: Int) = Page(
    data = data.map(SectionInfo::toSection),
    currentPage = currentPage,
    nextPage = paging?.nextPage
)

fun SectionInfo.toSection() = Section(
    id = id,
    title = title,
    type = when (type) {
        "vertical" -> SectionType.VERTICAL
        "horizontal" -> SectionType.HORIZONTAL
        "grid" -> SectionType.GRID
        else -> throw IllegalArgumentException("Unknown type: $type")
    }
)