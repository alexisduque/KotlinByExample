package io.monkeypatch.talks.waterpouring.server

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include


object Spec : Spek({ listOf(glassSpec, solverSpec, applicationSpec).forEach(::include) })
