package com.example.sqldelight.hockey.platform

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.GregorianCalendar

val defaultFormatter = DateFormatHelper("dd/MM/yyyy")

class DateFormatHelper constructor(format: String) {
  val dateFormatter = object : ThreadLocal<DateFormat>() {
    override fun initialValue(): DateFormat = SimpleDateFormat(format)
  }

  fun format(d: GregorianCalendar): String = dateFormatter.get()!!.format(d.time)
}
