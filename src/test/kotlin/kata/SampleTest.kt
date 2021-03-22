package kata

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SampleTest {
    @Test
    fun a_test() {
        val sampleClass = Sample()
        Assertions.assertTrue(sampleClass.returnsTrue())
    }
}