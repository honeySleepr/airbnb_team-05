//
//  AdvertisingCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/26.
//

import UIKit

final class AdvertisingCell: UICollectionViewCell {
    
    static let identifier = "AdvertisingCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        advertisingLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
        
    }
    
    let mainImageView: UIImageView = {
        let mainImage = UIImageView()
        return mainImage
    }()
    
    let mainExplain: UILabel = {
        let maintext = UILabel()
        maintext.numberOfLines = 0
        let font = UIFont(name: "Helvetica", size: 34)
        let attributedString = NSMutableAttributedString(string: "슬기로운\n자연생활")
        let length = attributedString.length
        attributedString.addAttributes([.foregroundColor:UIColor.black,
                                        .font:UIFont.systemFont(ofSize: 34, weight: .bold)],
                                       range: NSRange(location: 0, length: length))
        maintext.attributedText = attributedString
        return maintext
    }()
    
    let subExplain: UILabel = {
        let subText = UILabel()
        subText.numberOfLines = 0
        let subFont = UIFont(name: "Helvetica", size: 24)
        let subAttributedString = NSMutableAttributedString(string: "에어비앤비가 엄선한\n위시리스트를 만나보세요.")
        subText.attributedText = subAttributedString
        return subText
    }()
    
    let travelIdeaButton: UIButton = {
        let tempButton = UIButton()
        tempButton.setTitle("  여행 아이디어 얻기  ", for: .normal)
        tempButton.setTitleColor(UIColor.white, for: .normal)
        tempButton.backgroundColor = .black
        tempButton.titleLabel?.font = UIFont.systemFont(ofSize: 17, weight: .bold)
        tempButton.layer.cornerRadius = 10
        return tempButton
    }()
    
    func configure(image: UIImage?) {
        mainImageView.image = image
    }
    
    func appendSubView() {
        contentView.addSubview(mainImageView)
        contentView.addSubview(mainExplain)
        contentView.addSubview(subExplain)
        contentView.addSubview(travelIdeaButton)
    }
    
    func advertisingLayout() {
        mainImageView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            mainImageView.topAnchor.constraint(equalTo: contentView.topAnchor),
            mainImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            mainImageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            mainImageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor)
        ])
        
        mainExplain.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            mainExplain.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 24),
            mainExplain.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16)
        ])
        
        
        subExplain.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            subExplain.topAnchor.constraint(equalTo: mainExplain.bottomAnchor, constant: 20),
            subExplain.leadingAnchor.constraint(equalTo: mainExplain.leadingAnchor)
        ])
        
        travelIdeaButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            travelIdeaButton.topAnchor.constraint(equalTo: subExplain.bottomAnchor, constant: 20),
            travelIdeaButton.leadingAnchor.constraint(equalTo: mainExplain.leadingAnchor)
        ])
    }
}
