//
//  CollectionHeaderView.swift
//  Airbnb
//
//  Created by Jason on 2022/05/26.
//

import UIKit

class CollectionHeaderView: UICollectionReusableView {
    
    static let identifier = "headerView"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configuration()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
    }
    
    private var title: UILabel = {
        let tempTitle = UILabel()
        tempTitle.textColor = .black
        tempTitle.textAlignment = .left
        tempTitle.numberOfLines = 0
        return tempTitle
    }()
    
    func setHeaderText(text: String) {
        title.text = text
    }
    
    func setHeaderFontSize(size: CGFloat) {
        title.font = .systemFont(ofSize: size, weight: .init(rawValue: 400))
    }
    
    func configuration() {
        self.addSubview(title)
        title.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
        title.centerYAnchor.constraint(equalTo: centerYAnchor),
        title.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16),
        title.trailingAnchor.constraint(equalTo: trailingAnchor, constant: 16),
        ])
    }
}
