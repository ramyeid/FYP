# Load libraries

from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.svm import SVC
import pandas as pd
from sklearn import preprocessing
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
import sys
import os
from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn import svm
from sklearn.linear_model import SGDClassifier
from sklearn.ensemble import ExtraTreesClassifier
from sklearn.linear_model import RidgeClassifierCV
from sklearn.naive_bayes import BernoulliNB
from sklearn.neural_network import MLPClassifier


def ChooseAlgorithm(algorithmName):
	cwd = os.getcwd()
	cwd = cwd + "/src/main/resources/"
	if algorithmName=="GNB":
		return GaussianNB(), cwd + "GaussianNaiveBayes/"

	elif algorithmName == "KNN":
		return KNeighborsClassifier(), cwd+"KNearestNeighbors/"

	elif algorithmName == "LR":
		return LogisticRegression(),cwd+"LogisticRegression/"

	elif algorithmName == "LDA":
		return LinearDiscriminantAnalysis(), cwd +"LinearDiscriminant/"

	elif algorithmName == "DT":
		return DecisionTreeClassifier(), cwd +"DecisionTree/"

	elif algorithmName =="SVM":
		return SVC(),cwd+"SVM/"

	elif algorithmName == "RF":
		return RandomForestClassifier(),cwd+"RandomForest/"

	elif algorithmName =="GB":
		return GradientBoostingClassifier(),cwd+"GradientBoosting/"

	elif algorithmName == "LSVC":
		return svm.LinearSVC(), cwd + "LinearSVC/"

	elif algorithmName == "SGD":
		return SGDClassifier(), cwd+"StochasticGradientDescent/"

	elif algorithmName ==  "ET":
		return ExtraTreesClassifier(),cwd+"ExtraTreeClassifier/"

	elif algorithmName == "RC":
		return RidgeClassifierCV(), cwd+"RidgeClassifier/"

	elif algorithmName == "BNB":
		return BernoulliNB(),cwd+"BernoulliNaiveBayes/"
	elif algorithmName == "NN":
		return MLPClassifier(hidden_layer_sizes=depthResult), cwd+"NeuralNetwork/"


inputFile = sys.argv[1]
keyToPredict = sys.argv[2]
action = sys.argv[3]
actionTime = int(sys.argv[4])
algorithmName = sys.argv[5]
actionKeys = sys.argv[6]
hiddenDepth = sys.argv[7]

depthResult = []
if(hiddenDepth!=""):
	hiddenDepth = hiddenDepth.split(',')
	for i in range(0,len(hiddenDepth)):
		depth = int(hiddenDepth[i])
		print depth
		depthResult.append(depth)
else:
	depthResult.append(30)
	depthResult.append(30)
	depthResult.append(30)



clf,src = ChooseAlgorithm(algorithmName)
print 'src',src

data_df = pd.read_csv(inputFile,header=0)

actionKeys = actionKeys[1:].split('/')
keys = data_df.columns
keysToDrop = []
for i in range(0,len(keys)):
	if keys[i] not in actionKeys and keys[i]!=keyToPredict:
		keysToDrop.append(keys[i])


for j in range(len(keysToDrop)):
	data_df = data_df.drop(u""+keysToDrop[j],axis=1)







keys = data_df.columns

keyWithStringAvailable = False




keysWithStringValue =[]
for i in range(len(keys)):
	if (str(data_df[keys[i]][0]).replace('.','').isdigit() == False and str(data_df[keys[i]][0]) != ""):
		keyWithStringAvailable =True
		keysWithStringValue.append(keys[i])
	


                    	
data_df_rev = data_df



if keyWithStringAvailable == True:
	for value in keysWithStringValue:
		data_df_rev[value].replace(['?'], [data_df_rev.describe(include='all')[value][2]],inplace='True')
	keysWithStringValue = filter(lambda a: a != keyToPredict, keysWithStringValue)
	le = preprocessing.LabelEncoder()
	for i in range(len(keysWithStringValue)):
		data_df_rev[keysWithStringValue[i]+"_cat"] = le.fit_transform(data_df[keysWithStringValue[i]])
	data_df_rev = data_df_rev.drop(keysWithStringValue, axis = 1)
	

#drop the old categorical columns from dataframe



keys = data_df_rev.columns

keys = filter(lambda a: a != keyToPredict, keys)
keys.append(keyToPredict)

data_df_rev = data_df_rev.reindex_axis(keys, axis= 1)



num_features = keys
num_features.remove(keyToPredict)
scaled_features = {}
for each in num_features:
    mean, std = data_df_rev[each].mean(), data_df_rev[each].std()
    scaled_features[each] = [mean, std]
    data_df_rev.loc[:, each] = (data_df_rev[each] - mean)/std
    
    
size = len(data_df[keys[0]])

actionTime = int(actionTime)
if action == "2":
	index = size - actionTime
	for i in range(actionTime):
		data_df_rev[keyToPredict][index] = 0
   		index = index + 1




percentageActionTime = float(actionTime)/size

features = data_df_rev.values[:,:len(keys)]
target = data_df_rev.values[:,len(keys)]

# features_train, features_test, target_train, target_test = train_test_split(features,target, test_size = (percentageActionTime), random_state = 10)


target_test = target[size-actionTime:]
target_train = target[:size-actionTime]


features_train = features[:size-actionTime]
features_test = features[size-actionTime:]




clf.fit(features_train, target_train)
target_pred = clf.predict(features_test)


keys.append(keyToPredict)
if action == "1":
	result = accuracy_score(target_test, target_pred, normalize = True)
	df = pd.DataFrame()
	tmp_df = pd.read_csv(inputFile,header=0)
	originalKeys = tmp_df.columns

	originalKeys = filter(lambda a: a != keyToPredict, originalKeys)
	originalKeys.append(keyToPredict)
	for i in range(len(originalKeys)):
		df[originalKeys[i]] = tmp_df[originalKeys[i]]
	
	
	predicted_Actual = []
	for i in range(size-len(target_pred)):
		predicted_Actual.append(data_df[keyToPredict][i])
	
	for i in range(len(target_pred)):
		predicted_Actual.append(target_pred[i])
	
	df[keyToPredict+" Actual & Predicted"] = predicted_Actual
	df.to_csv(src+"Predicted_Actual_Result.csv",index=False)
	
	

	df_2 = pd.DataFrame()
	df_2[keyToPredict] = tmp_df[keyToPredict][(size-len(target_pred)):size]
	df_2[ keyToPredict + "Predicted"] = target_pred
	df_2.to_csv(src+"Predicted_Actual_Only.csv",index=False)

	with open(src+"Predicted_Actual_Only.csv",'a') as f:
		f.write("ACCURACY: "+str(result));
		f.write("\n")
		f.close()

	# print result

if action == "2":
	df = pd.DataFrame()
	tmp_df = pd.read_csv(inputFile,header=0)



	originalKeys = tmp_df.columns
	originalKeys = filter(lambda a: a != keyToPredict, originalKeys)
	for i in range(len(originalKeys)):
		df[originalKeys[i]] = tmp_df[originalKeys[i]]
		
		
	predicted_Actual = []
	for i in range(size-len(target_pred)):
		predicted_Actual.append(tmp_df[keyToPredict][i])
	
	for i in range(len(target_pred)):
		predicted_Actual.append(target_pred[i])
			
	df[keyToPredict+'Predict & Actual'] = predicted_Actual
	df.to_csv(src+"Predicted_result.csv",index=False)

	print target_pred
#     
