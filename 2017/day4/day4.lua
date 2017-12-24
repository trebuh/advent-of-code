#!/usr/bin/env luajit

function line_is_valid_part1(line)
    local matches = {}
    local matches_count = 0
    local size = 0
    
    for word in string.gmatch(line, "%S+") do
        size = size + 1
        if not matches[word] then
            matches[word] = true
            matches_count = matches_count + 1
        end
    end
    return size == matches_count
end

function get_alphabet ()
    local letters = {}
    for ascii = 97, 122 do 
        letters[string.char(ascii)] = 0
    end
    return letters
end

function are_palindroms(word, second_word)
    if string.len(word) ~= string.len(second_word) then
        return false
    else
        local letters = get_alphabet()
        local second_letters = get_alphabet()
        for char in word:gmatch"." do
            letters[char] = letters[char] + 1
        end
        for second_char in second_word:gmatch"." do
            second_letters[second_char] = second_letters[second_char] + 1
        end
        for ascii = 97, 122 do 
            local index = string.char(ascii)
            if letters[index] ~= second_letters[index] then
                return false
            end
        end
    end
    return true
end

function line_is_valid_part2(line)
    first_table = {}
    second_table =  {}
    for word in string.gmatch(line, "%S+") do
        table.insert(first_table, word)
        table.insert(second_table, word)
    end
    for i = 1, #first_table do
        for j = 1, #second_table do
            if i ~= j then
                if are_palindroms(first_table[i], second_table[j]) then
                    return false
                end
            end
        end
    end
    return true
end

local valid_pass_count_part1 = 0;
local valid_pass_count_part2 = 0;

for line in io.lines("input.txt") do
    
    if line_is_valid_part1(line) then
        valid_pass_count_part1 = valid_pass_count_part1 +1
    end

    if line_is_valid_part2(line) then
        valid_pass_count_part2 = valid_pass_count_part2 +1
    end

end

print("part1: ", valid_pass_count_part1)
print("part2: ", valid_pass_count_part2)
