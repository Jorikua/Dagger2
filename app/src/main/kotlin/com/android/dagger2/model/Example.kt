package com.android.dagger2.model

class Example {
    private var dependency: Dependency? = null

    fun setDependency(dependency: Dependency) {
        this.dependency = dependency
    }
}

//fun copy() {
//    var c: Char
//    while (true) {
//        c = readKeyboard()
//        if (c < 0) break
//        writePrinter(c)
//    }
//}
//
//fun readKeyboard(): Char {
//    return 'a'
//}
//
//fun readDisk(): Char {
//    return 'b'
//}
//
//fun writePrinter(c: Char) {
//
//}
//
//enum class InputDevice {
//    KEYBOARD, DISK
//}
//
//fun copy(inputDevice: InputDevice) {
//    var c: Char
//    while (true) {
//        when(inputDevice) {
//            InputDevice.KEYBOARD -> {
//                c = readKeyboard()
//            }
//            InputDevice.DISK -> {
//                c = readDisk()
//            }
//            else -> error("Whoops")
//        }
//
//        if (c < 0) break
//        writePrinter(c)
//    }
//}
//
//fun writeMonitor(c: Char) {
//
//}
//
//enum class OutputDevice {
//    PRINTER, MONITOR
//}
//
//fun copy(outputDevice: OutputDevice) {
//    var c: Char
//    while (true) {
//        c = readKeyboard()
//        if (c < 0) break
//        when(outputDevice) {
//            OutputDevice.PRINTER -> {
//                writePrinter(c)
//            }
//            OutputDevice.MONITOR -> {
//                writeMonitor(c)
//            }
//            else -> error("Whoops again!")
//        }
//    }
//}
//
//interface Reader {
//    fun read(): Char
//}
//
//interface Writer {
//    fun write(c: Char)
//}
//
//fun copy(reader: Reader, writer: Writer) {
//    var c: Char
//    while (true) {
//        c = reader.read()
//        if (c < 0) break
//        writer.write(c)
//    }
//}