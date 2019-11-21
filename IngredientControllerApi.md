# IngredientControllerApi

All URIs are relative to **

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewIngredientUsingPOST**](IngredientControllerApi.md#addNewIngredientUsingPOST) | **POST** /ingredients/recipe/{recipeid}/ingredient | addNewIngredient
[**deleteIngredientUsingDELETE**](IngredientControllerApi.md#deleteIngredientUsingDELETE) | **DELETE** /ingredients/recipe/{recipeid}/ingredient/{ingredientid} | deleteIngredient
[**updateIngredientUsingPUT**](IngredientControllerApi.md#updateIngredientUsingPUT) | **PUT** /ingredients/recipe/{recipeid}/ingredient/{ingredientid} | updateIngredient


## **addNewIngredientUsingPOST**

addNewIngredient

### Example
```bash
 addNewIngredientUsingPOST recipeid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ingredient** | [**Ingredient**](Ingredient.md) | ingredient |
 **recipeid** | **integer** | recipeid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteIngredientUsingDELETE**

deleteIngredient

### Example
```bash
 deleteIngredientUsingDELETE ingredientid=value recipeid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ingredientid** | **integer** | ingredientid |
 **recipeid** | **integer** | recipeid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **updateIngredientUsingPUT**

updateIngredient

### Example
```bash
 updateIngredientUsingPUT ingredientid=value recipeid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ingredientid** | **integer** | ingredientid |
 **recipeid** | **integer** | recipeid |
 **updateIngredient** | [**Ingredient**](Ingredient.md) | updateIngredient |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

