#!/usr/bin/env luajit

local valid_pass_count = 0;

for line in io.lines("input.txt") do

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
    
    if size == matches_count then
        valid_pass_count = valid_pass_count +1
    end

end

print(valid_pass_count)
