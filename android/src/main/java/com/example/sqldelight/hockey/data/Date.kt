package com.example.sqldelight.hockey.data

import com.squareup.sqldelight.ColumnAdapter
import java.util.GregorianCalendar

typealias Date = GregorianCalendar

class DateAdapter : ColumnAdapter<GregorianCalendar, Long> {
  override fun encode(value: GregorianCalendar) = value.timeInMillis
  override fun decode(databaseValue: Long) = GregorianCalendar.getInstance().apply {
    timeInMillis = databaseValue
  } as GregorianCalendar
}