
package ru.skillbranch.devintensive.extensions

import android.util.Log
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnit = TimeUnit.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnit.SECOND -> value * SECOND
        TimeUnit.MINUTE -> value * MINUTE
        TimeUnit.HOUR -> value * HOUR
        TimeUnit.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val interval =
        (((this.time - date.time) / 10) + 1) * 10 //тут кусок "(((А-В)/10)+1)*10" убирает погрешность около 7 мс вызванную временем выполнения программы
    if (interval > 0) {
        return when (interval) {
            in (0 * SECOND until 1 * SECOND) -> "только что ${this} - ${date} интервал: $interval"
            in (1 * SECOND until 45 * SECOND) -> "через несколько секунд ${this} - ${date} интервал: $interval"
            in (45 * SECOND until 75 * SECOND) -> "через минуту "
            in (75 * SECOND until 45 * MINUTE) -> "через ${interval / MINUTE} минут"
            in (45 * MINUTE until 75 * MINUTE) -> "через час"
            in (75 * MINUTE until 22 * HOUR) -> "через ${interval / HOUR} часов"
            in (22 * HOUR until 26 * HOUR) -> "через день"
            in (26 * HOUR until 360 * DAY) -> "через ${interval / DAY} дней"
            else -> "более чем через года $interval"
        }
    } else {
        return when (interval) {
            in (-1 * SECOND until -0 * SECOND) -> "только что ${this} - ${date} интервал: $interval"
            in (-45 * SECOND until -1 * SECOND) -> "несколько секунд назад ${this} - ${date} интервал: $interval"
            in (-75 * SECOND until -45 * SECOND) -> "минуту назад"
            in (-45 * MINUTE until -75 * SECOND) -> "${interval / MINUTE} минут назад"
            in (-75 * MINUTE until -45 * MINUTE) -> "час назад"
            in (-22 * HOUR until -75 * MINUTE) -> "${interval / HOUR} часов назад"
            in (-26 * HOUR until -22 * HOUR) -> "день назад"
            in (-360 * DAY until -26 * HOUR) -> "${interval / DAY} дней назад "
            else -> "более года назад $interval"
        }
    }

/*должна возвращать разницу между временем текущего экземпляра
и временем, которое передано в качестве аргумента*/


    /******************************************
     * должна возвращать разницу между временем текущего экземпляра
     * и временем, которое передано в качестве аргумента.
     *    0с - 1с "только что"
     *    1с - 45с "несколько секунд назад"
     *    45с - 75с "минуту назад"
     *    75с - 45мин "N минут назад"
     *    45мин - 75мин "час назад"
     *    75мин 22ч "N часов назад"
     *    22ч - 26ч "день назад"
     *    26ч - 360д "N дней назад"
     *    >360д "более года назад"
     *******************************************/
}

enum class TimeUnit {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
}