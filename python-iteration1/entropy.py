io = 7
poll = 0
student = 0
question = 0
answer = 0
db = 2

s = []
from math import log
l = [io, poll, student, question, answer, db]
for i in l:
    if i == 0:
        print('0 log')
        continue
    perc = i / sum(l)
    res = -1 * log(perc, 6) * perc
    s.append(res)
    print(res)

print()
print(sum(s))
