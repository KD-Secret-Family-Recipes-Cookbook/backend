# RecipesEndpointsApi

All URIs are relative to **

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewRecipeUsingPOST**](RecipesEndpointsApi.md#addNewRecipeUsingPOST) | **POST** /recipes/recipe | addNewRecipe
[**deleteRecipeByIdUsingDELETE**](RecipesEndpointsApi.md#deleteRecipeByIdUsingDELETE) | **DELETE** /recipes/recipe/{recipeid} | deleteRecipeById
[**findRecipeByCategorylikeUsingGET**](RecipesEndpointsApi.md#findRecipeByCategorylikeUsingGET) | **GET** /recipes/recipe/categorylike/{recipecategory} | findRecipeByCategorylike
[**findRecipeByIdUsingGET**](RecipesEndpointsApi.md#findRecipeByIdUsingGET) | **GET** /recipes/recipe/{recipeid} | findRecipeById
[**findRecipeByNameUsingGET**](RecipesEndpointsApi.md#findRecipeByNameUsingGET) | **GET** /recipes/{recipename} | findRecipeByName
[**findRecipeByNamelikeUsingGET**](RecipesEndpointsApi.md#findRecipeByNamelikeUsingGET) | **GET** /recipes/recipe/namelike/{recipename} | findRecipeByNamelike
[**listUserRecipesUsingGET**](RecipesEndpointsApi.md#listUserRecipesUsingGET) | **GET** /recipes/recipes | Returns all user&#39;s recipes
[**updateRecipeUsingPUT**](RecipesEndpointsApi.md#updateRecipeUsingPUT) | **PUT** /recipes/recipe/{recipeid} | updateRecipe
[**uploadImageUsingPOST**](RecipesEndpointsApi.md#uploadImageUsingPOST) | **POST** /recipes/recipe/{recipeid}/uploadimage | uploadImage


## **addNewRecipeUsingPOST**

addNewRecipe

### Example
```bash
 addNewRecipeUsingPOST
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **newRecipe** | [**Recipe**](Recipe.md) | newRecipe |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteRecipeByIdUsingDELETE**

deleteRecipeById

### Example
```bash
 deleteRecipeByIdUsingDELETE recipeid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **recipeid** | **integer** | recipeid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **findRecipeByCategorylikeUsingGET**

findRecipeByCategorylike

### Example
```bash
 findRecipeByCategorylikeUsingGET recipecategory=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **recipecategory** | **string** | recipecategory |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **findRecipeByIdUsingGET**

findRecipeById

### Example
```bash
 findRecipeByIdUsingGET recipeid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **recipeid** | **integer** | recipeid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **findRecipeByNameUsingGET**

findRecipeByName

### Example
```bash
 findRecipeByNameUsingGET recipename=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **recipename** | **string** | recipename |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **findRecipeByNamelikeUsingGET**

findRecipeByNamelike

### Example
```bash
 findRecipeByNamelikeUsingGET recipename=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **recipename** | **string** | recipename |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listUserRecipesUsingGET**

Returns all user's recipes

### Example
```bash
 listUserRecipesUsingGET
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**array[Recipe]**](Recipe.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **updateRecipeUsingPUT**

updateRecipe

### Example
```bash
 updateRecipeUsingPUT recipeid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **recipeid** | **integer** | recipeid |
 **updateRecipe** | [**Recipe**](Recipe.md) | updateRecipe |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **uploadImageUsingPOST**

uploadImage

### Example
```bash
 uploadImageUsingPOST recipeid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File** | file |
 **recipeid** | **integer** | recipeid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

