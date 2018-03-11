package io.monkeypatch.talks.waterpouring.server

import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.stubbing.OngoingStubbing

private fun assertThat(condition: Boolean, msg: () -> String) {
    if (!condition) throw AssertionError(msg())
}

infix fun <T> T.shouldBe(expected: T) =
    assertThat(this == expected) { "Expected $expected, got $this" }

infix fun <T> T.shouldNotBe(expected: T) =
    assertThat(this != expected) { "Expected not be $expected, got $this" }

infix fun <T> Collection<T>.shouldContainsAll(elements: Collection<T>) {
    elements.forEach {
        assertThat(contains(it)) { "$this expected to contain $it" }
    }
    this.forEach {
        assertThat(elements.contains(it)) { "$this not expected to contain $it" }
    }
}

infix fun <T> Collection<T>.shouldHaveSize(size: Int) =
    assertThat(this.size == size) { "$this expected to have size $size, got ${this.size}" }

infix fun <T> Collection<T>.shouldContainsOnly(element: T) {
    assertThat(contains(element)) { "$this expected to contain $element" }
    this.forEach {
        assertThat(it == element) { "$this not expected to contain $it" }
    }
}

// Mocking Sugar for Mockito

inline fun <reified T> mock(): T =
    mock(T::class.java)

fun <A> whenCalling(methodCall: A): OngoingStubbing<A> =
    Mockito.`when`(methodCall)
