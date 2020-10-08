import os
from collections import defaultdict
from nltk import trigrams
from nltk.corpus import PlaintextCorpusReader
corpus_root = os.getcwd() + "/"
file_ids = ".*.txt"
corpus1 = PlaintextCorpusReader("C:\\Users\AIDa_MAhmoud\Desktop\Secondterm\Corpus\Corpus\A\A", file_ids)
model = defaultdict(lambda: defaultdict(lambda: 0))
for sentence in corpus1.sents():
    #print("sentence" ,sentence)
    for w1, w2, w3 in trigrams(sentence, pad_right=True, pad_left=True):
        model[(w1, w2)][w3] += 1
# Let's transform the counts to probabilities
for w1_w2 in model:#print("w1_w2",w1_w2)
    total_count = float(sum(model[w1_w2].values()))   # print("total_count" ,total_count)
    for w3 in model[w1_w2]: # print("w3",w3 ,w1_w2)
        model[w1_w2][w3] /= total_count
text=[]
text = (input("please enter the words"))
print(text)
tex1=text.split(" ")
   # =tuple(text[0],text[1])
ch=tuple(model[tuple(tex1) ])
print(ch)