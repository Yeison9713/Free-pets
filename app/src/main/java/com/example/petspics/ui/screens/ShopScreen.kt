package com.example.petspics.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.petspics.data.models.*
import com.example.petspics.data.SampleData
import com.example.petspics.ui.components.ImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var selectedProductCategory by remember { mutableStateOf<ProductCategory?>(null) }
    var selectedServiceCategory by remember { mutableStateOf<ServiceCategory?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tienda",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Productos") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Servicios") }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        when (selectedTab) {
            0 -> ProductsSection(selectedCategory = selectedProductCategory) {
                selectedProductCategory = it
            }
            1 -> ServicesSection(selectedCategory = selectedServiceCategory) {
                selectedServiceCategory = it
            }
        }
    }
}

@Composable
fun ProductsSection(
    selectedCategory: ProductCategory?,
    onCategorySelect: (ProductCategory?) -> Unit
) {
    Column {
        // Categories
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { onCategorySelect(null) },
                    label = { Text("Todos") }
                )
            }
            
            items(ProductCategory.values()) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { onCategorySelect(category) },
                    label = { Text(getCategoryName(category)) }
                )
            }
        }
        
        // Products
        val filteredProducts = if (selectedCategory != null) {
            SampleData.sampleProducts.filter { it.category == selectedCategory }
        } else {
            SampleData.sampleProducts
        }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredProducts) { product ->
                ProductCard(product = product)
            }
        }
    }
}

@Composable
fun ServicesSection(
    selectedCategory: ServiceCategory?,
    onCategorySelect: (ServiceCategory?) -> Unit
) {
    Column {
        // Categories
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { onCategorySelect(null) },
                    label = { Text("Todos") }
                )
            }
            
            items(ServiceCategory.values()) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { onCategorySelect(category) },
                    label = { Text(getCategoryName(category)) }
                )
            }
        }
        
        // Services
        val filteredServices = if (selectedCategory != null) {
            SampleData.sampleServices.filter { it.category == selectedCategory }
        } else {
            SampleData.sampleServices
        }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredServices) { service ->
                ServiceCard(service = service)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /* TODO: Navigate to product details */ }
    ) {
        Row(
            modifier = Modifier.height(120.dp)
        ) {
            ImageLoader(
                imageUrl = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "COP ${String.format("%,.0f", product.price)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    RatingBar(rating = product.rating, reviews = product.reviews)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceCard(service: Service) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /* TODO: Navigate to service details */ }
    ) {
        Column {
            ImageLoader(
                imageUrl = service.imageUrl,
                contentDescription = service.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleMedium
                )
                
                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "COP ${String.format("%,.0f", service.price)}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = service.duration,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    
                    RatingBar(rating = service.rating, reviews = service.reviews)
                }
                
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                
                // Provider info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageLoader(
                        imageUrl = service.provider.imageUrl,
                        contentDescription = service.provider.name,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 8.dp),
                        contentScale = ContentScale.Crop
                    )
                    
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = service.provider.name,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = service.provider.location,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RatingBar(rating: Float, reviews: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color(0xFFFFB800)
        )
        Text(
            text = String.format("%.1f", rating),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "($reviews)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

fun getCategoryName(category: ProductCategory): String {
    return when (category) {
        ProductCategory.FOOD -> "Alimentos"
        ProductCategory.TOYS -> "Juguetes"
        ProductCategory.ACCESSORIES -> "Accesorios"
        ProductCategory.HEALTH -> "Salud"
        ProductCategory.GROOMING -> "Aseo"
    }
}

fun getCategoryName(category: ServiceCategory): String {
    return when (category) {
        ServiceCategory.GROOMING -> "Peluquería"
        ServiceCategory.VETERINARY -> "Veterinaria"
        ServiceCategory.TRAINING -> "Entrenamiento"
        ServiceCategory.WALKING -> "Paseos"
        ServiceCategory.DAYCARE -> "Guardería"
        ServiceCategory.BOARDING -> "Hospedaje"
    }
} 