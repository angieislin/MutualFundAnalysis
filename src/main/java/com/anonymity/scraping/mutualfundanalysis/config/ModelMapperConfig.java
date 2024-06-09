package com.anonymity.scraping.mutualfundanalysis.config;

import com.anonymity.scraping.mutualfundanalysis.dto.FundDetail;
import com.anonymity.scraping.mutualfundanalysis.entity.FundInfo;
import com.anonymity.scraping.mutualfundanalysis.entity.LatestFundDetail;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        addExtraMapping(modelMapper);
        return modelMapper;
    }

    private void addExtraMapping(ModelMapper modelMapper) {

        modelMapper.createTypeMap(FundInfo.class, FundDetail.class).addMappings(mapper -> {
            mapper.map(FundInfo::getName, FundDetail::setName);
            mapper.map(FundInfo::getCode, FundDetail::setCode);
            mapper.map(FundInfo::getManager, FundDetail::setManager);
            mapper.map(FundInfo::getEstablishDate, FundDetail::setEstablishDate);
            mapper.map(FundInfo::getType, FundDetail::setType);
        });

        modelMapper.createTypeMap(LatestFundDetail.class, FundDetail.class).addMappings(mapper -> {
            mapper.map(LatestFundDetail::getCurrentNavDate, FundDetail::setCurrentNavDate);
            mapper.map(LatestFundDetail::getLatestNetAssetValue, FundDetail::setLatestNetAssetValue);
            mapper.map(LatestFundDetail::getLastOneMonthReturn, FundDetail::setLastOneMonthReturn);
            mapper.map(LatestFundDetail::getLastThreeMonthReturn, FundDetail::setLastThreeMonthReturn);
            mapper.map(LatestFundDetail::getLastSixMonthReturn, FundDetail::setLastSixMonthReturn);
            mapper.map(LatestFundDetail::getLastOneYearReturn, FundDetail::setLastOneYearReturn);
            mapper.map(LatestFundDetail::getLastThreeYearReturn, FundDetail::setLastThreeYearReturn);
            mapper.map(LatestFundDetail::getSinceEstablishmentReturn, FundDetail::setSinceEstablishmentReturn);
        });
    }
}
