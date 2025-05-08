def freqCount(n, freq):
  while n:
    digit = n % Ten
    freq[digit] += 1
    n //= Ten

def digitAnagram(a): 
  result = []
  arr = []
  arr_temp = []
  for i in range(0, len(a)-1):
    for j in range(i+1, len(a)):
      freqA = [0] * Ten
      freqB = [0] * Ten
      freqCount(a[i], freqA)
      freqCount(a[j], freqB)
      # print(a[i])
      # print(a[j])
      if (freqA == freqB):
        arr.append(a[i])
        # print(arr)
        arr.append(a[j])
        # print(arr)
        arr_temp = arr.copy()
        result.append(arr_temp)
        arr.pop()
        arr.pop()
        # print(result)
  return len(result)


if __name__ == "__main__":
  Ten = 10
  a = [21, 12, 44, 43, 34]
  b = [25, 35, 872, 228, 53, 278, 872]
  print(digitAnagram(a))