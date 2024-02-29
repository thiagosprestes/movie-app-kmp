import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.localTimeZone

actual object DateTime {
    actual fun getFormattedDate(timestamp: String): String {
        val df = NSDateFormatter()
        val timestampFormat = "yyyy-MM-dd"
        val outputFormat = "dd MMMM yyyy"

        df.dateFormat = timestampFormat

        // Parse the GMT timestamp into an NSDate
        val date = df.dateFromString(timestamp)

        // Create a date formatter for the local time zone
        df.timeZone = NSTimeZone.localTimeZone
        df.dateFormat = outputFormat

        // Format the NSDate into a string in the local time zone
        return df.stringFromDate(date!!)
    }
}