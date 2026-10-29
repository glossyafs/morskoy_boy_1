fun createEmptyField(size: Int = 10): Array<CharArray> =
    Array(size) { CharArray(size) { '.' } }

fun printField(field: Array<CharArray>, title: String, showShips: Boolean = true, debug: Boolean = false) {
    if (debug) {
        println("=== $title (debug) ===")
        println("    " + "+---".repeat(field.size) + "+")
        for (r in field.indices) {
            print("$r | ")
            for (c in field.indices) {
                val cell = field[r][c]
                val symbol = if (cell == '#' && !showShips) '.' else cell
                print("$symbol | ")
            }
            println()
            println("    " + "+---".repeat(field.size) + "+")
        }
    } else {
        println("=== $title ===")
        print("  ")
        for (c in field.indices) print("$c ")
        println()
        for (r in field.indices) {
            print("$r ")
            for (c in field.indices) {
                val cell = field[r][c]
                val symbol = if (cell == '#' && !showShips) '.' else cell
                print("$symbol ")
            }
            println()
        }
    }
}

fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>, showEnemyShips: Boolean = false) {
    if (player.size != enemy.size) {
        println("Error: fields have different sizes!")
        return
    }

    println("=== Player field ===" + "          " + "=== Enemy field ===")

    print("  ")
    for (c in player.indices) print("$c ")
    print("     ")
    print("  ")
    for (c in enemy.indices) print("$c ")
    println()

    for (r in player.indices) {
        print("$r ")
        for (c in player.indices) print("${player[r][c]} ")

        print("     ")

        print("$r ")
        for (c in enemy.indices) {
            val cell = enemy[r][c]
            val symbol = if (cell == '#' && !showEnemyShips) '.' else cell
            print("$symbol ")
        }
        println()
    }
}

fun printFields(fields: Array<Array<CharArray>>, titles: Array<String>, showShips: BooleanArray) {
    if (fields.size != titles.size || fields.size != showShips.size) {
        println("Error: arrays sizes mismatch!")
        return
    }

    val size = fields[0].size
    for (field in fields) {
        if (field.size != size) {
            println("Error: fields have different sizes!")
            return
        }
    }

    for (i in fields.indices) {
        print("=== ${titles[i]} ===".padEnd(24))
    }
    println()

    for (i in fields.indices) {
        print("  ")
        for (c in 0 until size) print("$c ")
        print("     ")
    }
    println()

    for (r in 0 until size) {
        for (i in fields.indices) {
            print("$r ")
            for (c in 0 until size) {
                val cell = fields[i][r][c]
                val symbol = if (cell == '#' && !showShips[i]) '.' else cell
                print("$symbol ")
            }
            print("     ")
        }
        println()
    }
}

fun main() {
    // часть 1
    val playerField = createEmptyField()
    playerField[3][2] = '#'
    playerField[3][3] = '#'
    playerField[3][4] = '#'
    playerField[3][5] = '#'

    val enemyField = createEmptyField()
    enemyField[5][7] = '#'
    enemyField[6][7] = '#'
    enemyField[7][7] = '#'
    enemyField[5][5] = 'X'
    enemyField[5][6] = 'O'

    printField(playerField, "Player field")
    printField(enemyField, "Enemy field", showShips = false)

    // часть 2
    val smallField = createEmptyField(8)
    printField(smallField, "Small field 8x8")

    // часть 3
    val playerField3 = createEmptyField(10)
    playerField3[3][2] = '#'
    playerField3[3][3] = '#'

    val enemyField3 = createEmptyField(10)
    enemyField3[5][7] = '#'
    enemyField3[5][5] = 'X'

    printBothFields(playerField3, enemyField3)

    // часть 4
    val debugField = createEmptyField(5)
    debugField[2][2] = '#'
    printField(debugField, "Player field", debug = true)

    // часть 5
    val f1 = createEmptyField()
    val f2 = createEmptyField()
    val f3 = createEmptyField()
    f3[0][0] = 'X'

    val fieldsArray = arrayOf(f1, f2, f3)
    val titlesArray = arrayOf("Player field", "Enemy", "Third")
    val showArray = booleanArrayOf(true, false, false)

    printFields(fieldsArray, titlesArray, showArray)
}