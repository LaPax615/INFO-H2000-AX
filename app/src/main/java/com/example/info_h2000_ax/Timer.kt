import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay

import kotlinx.coroutines.*


class Timer {
    var time = 0

    companion object {
        const val SECONDS_PER_TICK = 1
    }

    suspend fun timer() {
        val channel = Channel<Int>()
        var seconds = 0
        while (true) {
            delay(SECONDS_PER_TICK * 1000L)
            seconds += SECONDS_PER_TICK
            channel.send(seconds)

            time = time + seconds

            //time = seconds
        }

    //time = channel
    }
}
