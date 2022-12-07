package io.github.cbinarycastle.kurly.data.section

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

data class PagingInfo(val nextPage: Int)