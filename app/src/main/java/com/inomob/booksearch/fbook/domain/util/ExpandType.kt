package com.inomob.booksearch.fbook.domain.util

// Expand types for the deciding which view expanded by useCase
// 'is' comparison is not working with enums so I used sealed class instead of enum.
// it helps eliminates the if/else clause and also heps easier the understand code
sealed class ExpandType {
    object CardViewDetail: ExpandType()
    object LanguageView: ExpandType()
    object TimeView: ExpandType()
    object PlacesView: ExpandType()
    object PeopleView: ExpandType()
    object FirstSentenceView:  ExpandType()
}
