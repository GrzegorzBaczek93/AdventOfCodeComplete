package y2015.d7

import utils.readInput

fun solution() {
    val input = readInput(2015, 7)

    println("Solved circuit ${solveCircuit(input)}")
}

private fun solveCircuit(input: List<String>): UShort {
    val instructions = parse(input)

    return solveInstruction(init = "a", instructions = instructions, mutableMapOf())
}

private fun solveInstruction(
    init: String,
    instructions: Map<String, Instruction>,
    memo: MutableMap<String, UShort>,
): UShort {
    memo[init]?.let { return it }
    val current = instructions[init] ?: return 0u

    fun resolveArgument(arg: String): UShort {
        return arg.toUShortOrNull() ?: solveInstruction(arg, instructions, memo)
    }

    return current.apply(init, ::resolveArgument).also { memo[init] = it }
}

private fun parse(input: List<String>): Map<String, Instruction> {
    val instruction = mutableMapOf<String, Instruction>()

    fun String.toDestination() = filter { it.isLetter() }

    input.forEach { raw ->
        val (r1, r2) = raw.split("->")
        instruction[r2.toDestination()] = r1.toInstruction()
    }

    return instruction
}

private fun String.toInstruction(): Instruction {
    fun getArg1() = this.split(" ").first()
    fun getArg2() = this.split(" ").dropLast(1).last()

    return when {
        contains("NOT") -> Instruction.OneParam.NOT(arg = this.filter { it.isLowerCase() })
        contains("AND") -> Instruction.TwoParam.AND(arg1 = getArg1(), arg2 = getArg2())
        contains("OR") -> Instruction.TwoParam.OR(arg1 = getArg1(), arg2 = getArg2())
        contains("LSHIFT") -> Instruction.TwoParam.LSHIFT(arg1 = getArg1(), arg2 = getArg2())
        contains("RSHIFT") -> Instruction.TwoParam.RSHIFT(arg1 = getArg1(), arg2 = getArg2())
        else -> Instruction.OneParam.ASSIGN(arg = this.filterNot { it.isWhitespace() })
    }
}

private sealed interface Instruction {
    fun apply(register: String, resolve: (arg: String) -> UShort): UShort

    sealed interface OneParam : Instruction {

        data class NOT(val arg: String) : OneParam {
            override fun apply(register: String, resolve: (arg: String) -> UShort): UShort = resolve(arg).inv()
        }

        data class ASSIGN(val arg: String) : OneParam {
            override fun apply(register: String, resolve: (arg: String) -> UShort): UShort = resolve(arg)
        }
    }

    sealed interface TwoParam : Instruction {
        data class AND(val arg1: String, val arg2: String) : TwoParam {
            override fun apply(register: String, resolve: (arg: String) -> UShort): UShort =
                resolve(arg1).and(resolve(arg2))
        }

        data class OR(val arg1: String, val arg2: String) : TwoParam {
            override fun apply(register: String, resolve: (arg: String) -> UShort): UShort =
                resolve(arg1).or(resolve(arg2))
        }

        data class LSHIFT(val arg1: String, val arg2: String) : TwoParam {
            override fun apply(register: String, resolve: (arg: String) -> UShort): UShort =
                (resolve(arg1).toUInt().shl(resolve(arg2).toInt())).toUShort()
        }

        data class RSHIFT(val arg1: String, val arg2: String) : TwoParam {
            override fun apply(register: String, resolve: (arg: String) -> UShort): UShort =
                (resolve(arg1).toUInt().shr(resolve(arg2).toInt())).toUShort()
        }
    }
}