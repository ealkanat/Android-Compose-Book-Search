package com.inomob.booksearch.fbook.domain.util

// Expand types for the deciding which view expanded by useCase
sealed class ExpandType {
    object CardViewDetail: ExpandType()
    object LanguageView: ExpandType()
    object TimeView: ExpandType()
    object PlacesView: ExpandType()
    object PeopleView: ExpandType()
    object FirstSentenceView:  ExpandType()
}
