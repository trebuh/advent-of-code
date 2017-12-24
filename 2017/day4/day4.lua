#!/usr/bin/env luajit

function line_is_valid_step1(line)
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

local valid_pass_count_step1 = 0;

for line in io.lines("input.txt") do
    
    if line_is_valid_step1(line) then
        valid_pass_count_step1 = valid_pass_count_step1 +1
    end

end

print(valid_pass_count_step1)
