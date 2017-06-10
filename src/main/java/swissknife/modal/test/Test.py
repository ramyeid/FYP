from sklearn import preprocessing
import sys
import os
import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

def ChooseAlgorithm(algorithmName):
    cwd = os.getcwd()
    cwd = cwd + "/src/main/resources/"
    if algorithmName=="LR":
        return LinearRegression(), cwd + "LinearRegression/"

inputFile = sys.argv[1]
keyToPredict = sys.argv[2]
action = sys.argv[3]
actionTime = sys.argv[4]
algorithmName = sys.argv[5]


clf,src = ChooseAlgorithm(algorithmName)
print 'src',src


data_df = pd.read_csv(inputFile, header=0)

keyWithStringAvailable = False

keys = data_df.columns
keysWithStringValue = []
for i in range(len(keys)):
    if (str(data_df[keys[i]][0]).replace('.', '').isdigit() == False and str(data_df[keys[i]][0]) != ""):
        keyWithStringAvailable = True
        keysWithStringValue.append(keys[i])

data_df_rev = data_df

if keyWithStringAvailable == True:
    for value in keysWithStringValue:
        data_df_rev[value].replace(['?'], [data_df_rev.describe(include='all')[value][2]], inplace='True')
    keysWithStringValue.remove(keyToPredict)
    le = preprocessing.LabelEncoder()
    for i in range(len(keysWithStringValue)):
        data_df_rev[keysWithStringValue[i] + "_cat"] = le.fit_transform(data_df[keysWithStringValue[i]])
    data_df_rev = data_df_rev.drop(keysWithStringValue, axis=1)

# drop the old categorical columns from dataframe



keys = data_df_rev.columns

keys = filter(lambda a: a != keyToPredict, keys)
keys.append(keyToPredict)

data_df_rev = data_df_rev.reindex_axis(keys, axis=1)

num_features = keys
num_features.remove(keyToPredict)
scaled_features = {}
for each in num_features:
    mean, std = data_df_rev[each].mean(), data_df_rev[each].std()
    scaled_features[each] = [mean, std]
    data_df_rev.loc[:, each] = (data_df_rev[each] - mean) / std

size = len(data_df[keys[0]])

actionTime = int(actionTime)

if action == "2":
    index = size - actionTime
    for i in range(actionTime):
        data_df_rev[keyToPredict][index] = 0
        index = index + 1

percentageActionTime = float(actionTime) / size

features = data_df_rev.values[:, :len(keys)]
target = data_df_rev.values[:, len(keys)]

# features_train, features_test, target_train, target_test = train_test_split(features,target, test_size = (percentageActionTime), random_state = 10)


target_test = target[size - actionTime:]
target_train = target[:size - actionTime]

features_train = features[:size - actionTime]
features_test = features[size - actionTime:]


clf.fit(features_train, target_train)
target_pred = clf.predict(features_test)

keys.append(keyToPredict)
if action == "1":
    error = mean_squared_error(target_test, target_pred)
    print 'error', error
    print 'AAAA', np.mean((target_test - target_pred) ** 2)

    df = pd.DataFrame()
    tmp_df = pd.read_csv(inputFile, header=0)
    originalKeys = tmp_df.columns

    originalKeys = filter(lambda a: a != keyToPredict, originalKeys)
    originalKeys.append(keyToPredict)
    for i in range(len(originalKeys)):
        df[originalKeys[i]] = tmp_df[originalKeys[i]]

    predicted_Actual = []
    for i in range(size - len(target_pred)):
        predicted_Actual.append(data_df[keyToPredict][i])

    for i in range(len(target_pred)):
        predicted_Actual.append(target_pred[i])

    df[keyToPredict + " Actual & Predicted"] = predicted_Actual
    df.to_csv(src + "Predicted_Actual_Result.csv")

    df_2 = pd.DataFrame()
    df_2[keyToPredict] = tmp_df[keyToPredict][(size - len(target_pred)):size]
    df_2[keyToPredict + "Predicted"] = target_pred
    df_2.to_csv(src + "Predicted_Actual_Only.csv")

    with open(src + "Predicted_Actual_Only.csv", 'a') as f:
        f.write("ERROR MSE: " + str(error));
        f.write("\n")
        f.close()

        # print result

if action == "2":
    df = pd.DataFrame()
    tmp_df = pd.read_csv(inputFile, header=0)
    originalKeys = tmp_df.columns

    originalKeys = filter(lambda a: a != keyToPredict, originalKeys)
    for i in range(len(originalKeys)):
        df[originalKeys[i]] = tmp_df[originalKeys[i]]

    predicted_Actual = []
    for i in range(size - len(target_pred)):
        predicted_Actual.append(tmp_df[keyToPredict][i])

    for i in range(len(target_pred)):
        predicted_Actual.append(target_pred[i])

    df[keyToPredict + 'Predict & Actual'] = predicted_Actual
    df.to_csv(src + "Predicted_result.csv")

    print target_pred
