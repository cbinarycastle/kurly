package io.github.cbinarycastle.kurly.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

inline fun <reified T> List<Flow<T>>.combineAll() = combine(this) { it.toList() }